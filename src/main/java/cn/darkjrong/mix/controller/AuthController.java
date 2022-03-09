package cn.darkjrong.mix.controller;

import cn.darkjrong.mix.common.pojo.dto.*;
import cn.darkjrong.mix.common.pojo.vo.PermissionVO;
import cn.darkjrong.mix.common.pojo.vo.ResponseVO;
import cn.darkjrong.mix.common.pojo.vo.RoleVO;
import cn.darkjrong.mix.common.pojo.vo.UserInfoVO;
import cn.darkjrong.mix.service.AuthService;
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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 身份验证控制器
 *
 * @author Rong.Jia
 * @date 2022/03/02
 */
@Slf4j
@Validated
@Api(tags = "权限管理")
@RestController
@RequestMapping("/")
public class AuthController extends AbstractController {

    @Autowired
    private AuthService authService;

    @ApiOperation("添加用户信息")
    @PostMapping(value = "user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<Void> saveUserInfo(@RequestBody @Valid UserInfoDTO userInfoDTO) {

        log.info("saveUserInfo {}", userInfoDTO.toString());

        authService.saveUserInfo(userInfoDTO);

        return ResponseVO.success();
    }

    @ApiOperation("修改用户信息")
    @PutMapping(value = "user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<Void> updateUserInfo(@RequestBody @Valid UserInfoDTO userInfoDTO) {

        log.info("updateUserInfo {}", userInfoDTO.toString());

        authService.updateUserInfo(userInfoDTO);

        return ResponseVO.success();
    }

    @ApiOperation("删除用户信息")
    @DeleteMapping(value = "user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "userId", dataTypeClass = Long.class, value = "用户ID", required = true)
    })
    public ResponseVO<Void> deleteUserInfo(@PathVariable("userId") @NotNull(message = "用户ID 不能为空") Long userId) {

        log.info("deleteUserInfo {}", userId);

        authService.deleteUserInfoById(userId);
        return ResponseVO.success();
    }

    @ApiOperation("查询用户信息")
    @GetMapping(value = "user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<UserInfoVO>> findUserInfo(UserInfoFilterDTO filterDTO) {

        log.info("findUserInfo {}", filterDTO.toString());

        ValidationUtil.validate(filterDTO);
        return ResponseVO.success(authService.findUserInfos(filterDTO));
    }

    @ApiOperation("根据账号查询用户信息")
    @GetMapping(value = "user/account/{account}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "account", dataTypeClass = String.class, value = "账号", required = true)
    })
    public ResponseVO<UserInfoVO> queryUserInfoByAccount(@PathVariable("account") @NotBlank(message = "账号 不能为空") String account) {

        log.info("queryUserInfoByAccount {}", account);
        return ResponseVO.success(authService.findUserByAccount(account));
    }

    @ApiOperation("根据用户ID查询用户信息")
    @GetMapping(value = "user/id/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "userId", dataTypeClass = Long.class, value = "用户ID", required = true)
    })
    public ResponseVO<UserInfoVO> queryUserInfoById(@PathVariable("userId") @NotNull(message = "用户ID 不能为空") Long userId) {

        log.info("queryUserInfoById {}", userId);
        return ResponseVO.success(authService.findUserInfoByUserId(userId));
    }

    @ApiOperation("添加角色信息")
    @PostMapping(value = "role", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<Void> saveRole(@RequestBody @Valid RoleDTO roleDTO) {

        log.info("saveRole {}", roleDTO.toString());

        roleDTO.setCreatedUser(getAccount());
        authService.saveRole(roleDTO);

        return ResponseVO.success();
    }

    @ApiOperation("修改角色信息")
    @PutMapping(value = "role", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<Void> updateRole(@RequestBody @Valid RoleDTO roleDTO) {

        log.info("updateRole {}", roleDTO.toString());

        roleDTO.setUpdatedUser(getAccount());
        authService.updateRole(roleDTO);

        return ResponseVO.success();
    }

    @ApiOperation("删除角色信息")
    @DeleteMapping(value = "role/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "id", dataTypeClass = Long.class, value = "角色ID", required = true)
    })
    public ResponseVO<Void> deleteRole(@PathVariable("id") @NotNull(message = "角色ID 不能为空") Long id) {

        log.info("deleteRole {}", id);

        authService.deleteRoleById(id);
        return ResponseVO.success();
    }

    @ApiOperation("查询角色信息")
    @GetMapping(value = "role", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<RoleVO>> findRole(RoleFilterDTO filterDTO) {

        log.info("findRole {}", filterDTO.toString());

        ValidationUtil.validate(filterDTO);
        return ResponseVO.success(authService.findRoles(filterDTO));
    }

    @ApiOperation("添加权限菜单信息")
    @PostMapping(value = "permission", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<Void> savePermission(@RequestBody @Valid PermissionDTO permissionDTO) {

        log.info("savePermission {}", permissionDTO.toString());

        permissionDTO.setCreatedUser(getAccount());
        authService.savePermission(permissionDTO);

        return ResponseVO.success();
    }

    @ApiOperation("修改权限菜单信息")
    @PutMapping(value = "permission", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<Void> updatePermission(@RequestBody @Valid PermissionDTO permissionDTO) {

        log.info("updatePermission {}", permissionDTO.toString());

        permissionDTO.setUpdatedUser(getAccount());
        authService.updatePermission(permissionDTO);

        return ResponseVO.success();
    }

    @ApiOperation("删除权限菜单信息")
    @DeleteMapping(value = "permission/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "id", dataTypeClass = Long.class, value = "权限菜单ID", required = true)
    })
    public ResponseVO<Void> deletePermission(@PathVariable("id") @NotNull(message = "权限菜单ID 不能为空") Long id) {

        log.info("deletePermission {}", id);

        authService.deletePermissionById(id);
        return ResponseVO.success();
    }

    @ApiOperation("根据角色ID查询权限树")
    @GetMapping(value = "permission/tree/{roleId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "roleId", dataTypeClass = Long.class, value = "角色ID", required = true)
    })
    public ResponseVO<List<PermissionVO>> queryPermissionTreeByRoleId(@PathVariable("roleId") @NotNull(message = "角色ID 不能为空") Long roleId) {

        log.info("queryPermissionTreeByRoleId {}", roleId);
        return ResponseVO.success(authService.queryPermissionTreeByRoleId(roleId));
    }

    @ApiOperation("查询权限菜单信息")
    @GetMapping(value = "permission", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<PermissionVO>> findPermission(PermissionFilterDTO filterDTO) {

        log.info("findPermission {}", filterDTO.toString());

        ValidationUtil.validate(filterDTO);
        return ResponseVO.success(authService.findPermissions(filterDTO));
    }

    @ApiOperation("获取未分配用户")
    @GetMapping(value = "user/undistributed", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<List<UserInfoVO>> queryUnassignedUser() {

        log.info("queryUnassignedUser {}", System.currentTimeMillis());
        return ResponseVO.success(authService.queryUnassignedUser());
    }

    @ApiOperation("用户授权角色")
    @PatchMapping(value = "userRole", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<Void> userRoleAuthorization(@RequestBody @Valid UserRoleAuthDTO userRoleAuthDTO) {

        log.info("userRoleAuthorization {}", userRoleAuthDTO.toString());

        authService.userRoleAuthorization(userRoleAuthDTO);
        return ResponseVO.success();
    }

    @ApiOperation("用户授权角色")
    @PatchMapping(value = "rolePermission", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<Void> rolePermissionAuth(@RequestBody @Valid RolePermissionAuthDTO rolePermissionAuthDTO) {

        log.info("rolePermissionAuth {}", rolePermissionAuthDTO.toString());

        authService.rolePermissionAuth(rolePermissionAuthDTO);
        return ResponseVO.success();
    }

    @ApiOperation("修改用户密码")
    @PatchMapping(value = "user/pwd", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<Void> updatePwd(@RequestBody @Valid PwdDTO pwdDTO) {

        log.info("updatePwd {}", pwdDTO.toString());

        authService.modifyPwd(pwdDTO);
        return ResponseVO.success();
    }

    @ApiOperation("重置用户密码")
    @PatchMapping(value = "user/{account}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "account", dataTypeClass = String.class, value = "用户账号", required = true)
    })
    public ResponseVO<String> resetPwd(@PathVariable("account") @NotNull(message = "账号不能为空") String account) {

        log.info("resetPwd {}", account);
        return ResponseVO.success(authService.resetPwd(account));
    }

    @ApiOperation("启/禁用账号")
    @PatchMapping(value = "user/disable/{account}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "account", dataTypeClass = String.class, value = "用户账号", required = true)
    })
    public ResponseVO<Void> disableUserInfo(@PathVariable("account") @NotNull(message = "账号不能为空") String account) {

        log.info("disableUserInfo {}", account);

        authService.disableUserInfo(account);
        return ResponseVO.success();

    }

    @ApiOperation("延长账号使用时间")
    @PatchMapping(value = "user/extended", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<Void> extensionValidity(@RequestBody @Validated ExtensionValidityDTO extensionValidityDTO) {

        log.info("extensionValidity {}", extensionValidityDTO);

        authService.extensionValidity(extensionValidityDTO);
        return ResponseVO.success();
    }

    @ApiOperation("启/禁用角色")
    @PatchMapping(value = "role/disable/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "id", dataTypeClass = Long.class, value = "角色id", required = true)
    })
    public ResponseVO<Void> disableRole(@PathVariable("id") @NotNull(message = "角色id不能为空") Long id) {

        log.info("disableRole {}", id);

        authService.disableRole(id);
        return ResponseVO.success();
    }

    @ApiOperation("根据角色ID查询用户信息")
    @GetMapping(value = "user/role/{roleId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "roleId", dataTypeClass = Long.class, value = "角色ID", required = true)
    })
    public ResponseVO<List<UserInfoVO>> queryUserInfoByRoleId(@PathVariable("roleId") @NotNull(message = "角色ID不能为空") Long roleId) {

        log.info("queryUserInfoByRoleId {}", roleId);
        return ResponseVO.success(authService.queryUserInfoByRoleId(roleId));
    }




}
