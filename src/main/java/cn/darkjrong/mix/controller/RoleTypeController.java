package cn.darkjrong.mix.controller;

import cn.darkjrong.mix.common.pojo.dto.RoleTypeDTO;
import cn.darkjrong.mix.common.pojo.dto.RoleTypeFilterDTO;
import cn.darkjrong.mix.common.pojo.vo.ResponseVO;
import cn.darkjrong.mix.common.pojo.vo.RoleTypeVO;
import cn.darkjrong.mix.service.RoleTypeService;
import cn.darkjrong.pager.vo.PageVO;
import cn.hutool.extra.validation.ValidationUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 角色类型信息 前端控制器
 * </p>
 *
 * @author Rong.Jia
 * @since 2022-02-24
 */
@Api(tags = "角色类型管理")
@Slf4j
@Validated
@RestController
@RequestMapping("/role-type")
public class RoleTypeController extends AbstractController {

    @Autowired
    private RoleTypeService roleTypeService;

    @ApiOperation("添加角色类型信息")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<Void> saveRoleType(@RequestBody @Valid RoleTypeDTO roleTypeDTO) {

        log.info("saveRoleType {}", roleTypeDTO.toString());

        roleTypeDTO.setCreatedUser(getAccount());
        roleTypeService.saveRoleType(roleTypeDTO);

        return ResponseVO.success();
    }

    @ApiOperation("修改角色类型信息")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<Void> updateRoleType(@RequestBody @Valid RoleTypeDTO roleTypeDTO) {

        log.info("updateRoleType {}", roleTypeDTO.toString());

        roleTypeDTO.setUpdatedUser(getAccount());
        roleTypeService.updateRoleType(roleTypeDTO);

        return ResponseVO.success();
    }

    @ApiOperation("删除角色类型信息")
    @DeleteMapping(value = "/{roleTypeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "roleTypeId", dataTypeClass = Long.class, value = "角色类型ID", required = true)
    })
    public ResponseVO<Void> deleteRoleType(@PathVariable("roleTypeId") @NotNull(message = "角色类型ID 不能为空") Long roleTypeId) {

        log.info("deleteRoleType {}", roleTypeId);

        roleTypeService.deleteRoleType(roleTypeId);
        return ResponseVO.success();
    }

    @ApiOperation("查询角色类型信息")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<RoleTypeVO>> findRoleType(RoleTypeFilterDTO filterDTO) {

        log.info("findRoleType {}", filterDTO.toString());

        ValidationUtil.validate(filterDTO);
        return ResponseVO.success(roleTypeService.queryRoleType(filterDTO));
    }


}
