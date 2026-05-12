package com.ZXKC.controller;

import com.ZXKC.domain.Dish;
import com.ZXKC.domain.Result;
import com.ZXKC.service.DishService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品模块Controller
 * 对应UI功能：商品列表展示、关键词搜索、点击商品弹出详情
 * 端口：8080
 */
@RestController
@RequestMapping("/dish")
public class DishController {

    @Resource
    private DishService dishService;

    /**
     * 商品分页查询 (UI主界面商品列表)
     * 支持：分页加载 + 分类切换
     * 调用地址：GET http://localhost:8080/dish/page
     */
    @GetMapping("/page")
    public Result<Page<Dish>> page(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "20") Integer pageSize,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) String keyword
    ) {
        Page<Dish> dishPage = dishService.pageDish(pageNum, pageSize, categoryId, keyword);
        return Result.success(dishPage);
    }

    // ===================== 新增：按商品名称模糊查询接口（搜索框专用）=====================
    /**
     * 根据商品名称模糊查询（适配UI搜索框）
     * 调用地址：GET http://localhost:8080/dish/search?name=宫保鸡丁
     */
    @GetMapping("/search")
    public Result<List<Dish>> searchByName(@RequestParam String name) {
        List<Dish> dishList = dishService.lambdaQuery()
                .like(Dish::getName, name)
                .eq(Dish::getStatus, 1)
                .list();
        return Result.success(dishList);
    }
    // ==================================================================================

    /**
     * 商品详情查询 (点击商品弹出弹窗)
     * 调用地址：GET http://localhost:8080/dish/detail/1
     */
    @GetMapping("/detail/{id}")
    public Result<Dish> detail(@PathVariable Long id) {
        Dish dish = dishService.getDishById(id);
        return Result.success(dish);
    }
}