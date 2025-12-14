package com.qishanor.system.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qishanor.common.constant.CacheConstant;
import com.qishanor.common.excel.annotation.ResponseExcel;
import com.qishanor.common.util.R;
import com.qishanor.system.model.SysDict;
import com.qishanor.system.model.SysDictItem;
import com.qishanor.system.model.dto.DictItemDto;
import com.qishanor.system.model.dto.DictDto;
import com.qishanor.system.model.vo.DictItemVo;
import com.qishanor.system.service.SysDictItemService;
import com.qishanor.system.service.SysDictService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 字典表 前端控制器
 */
@RestController
@AllArgsConstructor
@RequestMapping("/admin/dict")
@Tag(description = "dict", name = "字典管理模块")
@SaCheckLogin
public class SysDictController {

    private final SysDictService dictService;

    private final SysDictItemService dictItemService;



    /**
     * 分页查询字典信息
     */
    @GetMapping("/page")
    @SaCheckPermission("sys_dict_view")
    public R getDictPage(@ParameterObject Page page, @ParameterObject SysDict sysDict) {
        return R.ok(dictService.page(page,
                Wrappers.<SysDict>lambdaQuery()
                        .eq(StrUtil.isNotBlank(sysDict.getSystemFlag()), SysDict::getSystemFlag, sysDict.getSystemFlag())
                        .like(StrUtil.isNotBlank(sysDict.getDictType()), SysDict::getDictType, sysDict.getDictType())));
    }


    /**
     * 分页查询
     * @param name 名称或者字典项
     */
    @GetMapping("/list")
    public R getDictList(String name) {
        return R.ok(dictService.list(Wrappers.<SysDict>lambdaQuery()
                .like(StrUtil.isNotBlank(name), SysDict::getDictType, name)
                .or()
                .like(StrUtil.isNotBlank(name), SysDict::getDescription, name)
                .orderByDesc(SysDict::getCreateTime)));
    }

    /**
     * 通过ID查询字典信息
     */
    @GetMapping("/detail/{id}")
    public R getById(@PathVariable Long id) {
        return R.ok(dictService.getById(id));
    }

    /**
     * 查询字典信息
     */
    @GetMapping("/detail")
    public R getDetails(@ParameterObject DictDto query) {
        SysDict dict=BeanUtil.copyProperties(query, SysDict.class);
        return R.ok(dictService.getOne(Wrappers.query(dict)));
    }

    /**
     * 通过字典类型查找字典
     */
    @GetMapping("/type/{type}")
    @Cacheable(value = CacheConstant.DICT_DETAIL, key = "#type", unless = "#result.data.isEmpty()")
    public R getDictByType(@PathVariable String type) {
        return R.ok(dictItemService.list(Wrappers.<SysDictItem>query()
                .lambda()
                .eq(SysDictItem::getDictType, type)
                .orderByDesc(SysDictItem::getSortOrder)));
    }

    /**
     * 添加字典
     */
    @PostMapping
    @SaCheckPermission("sys_dict_add")
    public R save(@Valid @RequestBody DictDto dictDto) {
        SysDict dict= BeanUtil.copyProperties(dictDto, SysDict.class);
        dictService.save(dict);
        return R.ok(dict);
    }

    /**
     * 修改字典
     */
    @PutMapping
    @SaCheckPermission("sys_dict_edit")
    public R updateById(@Valid @RequestBody DictDto dictDto) {
        return dictService.updateDict(dictDto);
    }
    /**
     * 删除字典，并且清除字典缓存
     *
     * @param ids ID
     * @return R
     */
    @DeleteMapping
    @SaCheckPermission("sys_dict_del")
    public R removeById(@RequestBody Long[] ids) {
        return R.ok(dictService.removeDictByIds(ids));
    }





    /**
     * item
     * 分页查询
     *
     */
    @GetMapping("/item/page")
    public R getSysDictItemPage(Page page, SysDictItem sysDictItem) {
        Page<SysDictItem> result = dictItemService.page(page, Wrappers.query(sysDictItem));
        return R.ok(result);
    }

    /**
     * 通过id查询字典项
     */
    @GetMapping("/item/detail/{id}")
    public R getDictItemById(@PathVariable("id") Long id) {
        SysDictItem dictItem = dictItemService.getById(id);
        DictItemVo vo= BeanUtil.copyProperties(dictItem, DictItemVo.class);
        return R.ok(vo);
    }

    /**
     * 查询字典项详情
     */
    @GetMapping("/item/detail")
    public R getDictItemDetails(DictItemDto itemDto) {
        SysDictItem query = BeanUtil.copyProperties(itemDto, SysDictItem.class);

        SysDictItem dictItem = dictItemService.getOne(Wrappers.query(query));
        DictItemVo vo= BeanUtil.copyProperties(dictItem, DictItemVo.class);
        return R.ok(vo);
    }

    /**
     * 新增字典项
     */
    @PostMapping("/item")
    @CacheEvict(value = CacheConstant.DICT_DETAIL, allEntries = true)
    public R save(@RequestBody @Validated DictItemDto itemDto) {
        SysDictItem dictItem = BeanUtil.copyProperties(itemDto, SysDictItem.class);
        SysDict dbDict = dictService.getById(itemDto.getDictId());
        if(ObjectUtil.isNull(dbDict)){
            return R.failed("字典不存在");
        }
        dictItem.setDictType(dbDict.getDictType());
        dictItemService.save(dictItem);
        return R.ok();
    }

    /**
     * 修改字典项
     */
    @PutMapping("/item")
    public R updateById(@RequestBody @Validated DictItemDto itemDto) {
        SysDictItem dictItem = BeanUtil.copyProperties(itemDto, SysDictItem.class);
        return dictItemService.updateDictItem(dictItem);
    }

    /**
     * 通过id删除字典项
     */
    @DeleteMapping("/item/{id}")
    public R removeDictItemById(@PathVariable Long id) {
        return dictItemService.removeDictItem(id);
    }

    /**
     * 同步缓存字典
     */
    @PutMapping("/sync")
    public R sync() {
        return dictService.syncDictCache();
    }

    @ResponseExcel
    @GetMapping("/export")
    public List<DictItemVo> export() {
        List<SysDictItem> lists = dictItemService.list(Wrappers.emptyWrapper());
        List<DictItemVo> results=lists.stream().map(item->{
            DictItemVo vo=BeanUtil.copyProperties(item, DictItemVo.class);
            return vo;
        }).toList();

        return results;
    }

}
