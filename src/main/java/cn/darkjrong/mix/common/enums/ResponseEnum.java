package cn.darkjrong.mix.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 响应枚举
 *
 * @author Rong.Jia
 * @date 2021/07/28 09:16:48
 */
@Getter
@AllArgsConstructor
public enum ResponseEnum {

    /**
     * 枚举类code 开头使用规则：
     * 0: 成功；
     * -1: 失败；
     * 1：参数不正确
     * <p>
     * 1000：公共
     */

    // 成功
    SUCCESS(0, "成功"),

    // 参数不正确
    PARAMETER_ERROR(1, "参数不正确"),

    // 失败
    ERROR(-1, "失败"),
    SYSTEM_ERROR(-1, "系统错误"),
    FILE_LIMIT_EXCEEDED(-1, "文件超出限制, 请选择较小文件"),
    INT404_NOT_FOUND(-1, "找不到请求接口"),
    INT400_BAD_REQUEST(-1, "请求参数或方式错误"),


    // 未找到
    NOT_FOUND(404, "请求接口不存在"),

    // 请求方式错误
    REQUEST_MODE_ERROR(405, "请求方式错误, 请检查"),

    //媒体类型不支持
    MEDIA_TYPE_NOT_SUPPORTED(415, "媒体类型不支持"),

    SERVICE_EXCEPTIONS(4000, "服务异常, 请联系管理员"),

    ACCESS_TOKEN_INVALID(401, "access_token无效"),
    REFRESH_TOKEN_INVALID(401, "refresh_token无效"),
    UNAUTHORIZED(401, "无权访问(未授权)"),
    AUTHORIZATION_EXPIRES(401, "授权过期, 请求重新登录"),
    NOT_LOGGED_IN(401, "未登录，或者授权过期"),
    ANONYMOUS_SUBJECT_UNAUTHORIZED(401, "无权访问:当前用户是匿名用户，请先登录"),
    AUTHENTICATION_FAILED(401, "身份验证未通过"),
    MISSING_TOKEN_AUTHENTICATION_FAILED(401, "缺失令牌,鉴权失败"),
    THE_ACCOUNT_HAS_BEEN_LOGGED_IN_TO_THE_REMOTE_SITE_PLEASE_LOG_IN_AGAIN(401, "账号已在异地登录，请重新登录"),
    THE_USER_DOES_NOT_EXIST_REPLACE_THE_USER(401, "用户不存在, 请更换用户"),

    /*-------------------------common begin----------------------------------------*/

    // 1000：公共
    REQUEST_PARAMETER_FORMAT_IS_INCORRECT(1000, "请求参数格式不正确"),
    ENCRYPTION_OR_DECRYPTION_FAILED(1001, "加密/解密失败"),
    WEBSOCKET_CONNECTION_FAILED(1002, "websocket 连接失败， 请重试"),
    WEBSOCKET_CONNECTION_SUCCEEDED(SUCCESS.getCode(), "websocket 连接成功"),
    WEBSOCKET_HEART_KEEP(SUCCESS.getCode(), "heart_keep"),
    WEBSOCKET_USER_ID_NO_NULL(1003, "连接websocket 必须指定userId"),
    THE_ID_CANNOT_BE_EMPTY(1004, "id 不能为空"),
    THE_NAME_CANNOT_BE_EMPTY(1005, "名称不能为空"),
    DATA_QUOTE(1006, "数据被引用，无法执行操作"),
    TIME_IS_EMPTY(1007, "时间为空"),
    THE_PHONE_CANNOT_BE_EMPTY(1008, "联系电话不能为空"),
    THE_PHONE_ALREADY_EXISTS(1009, "联系电话已存在"),
    INVALID_SPECIFIED_STATE(1010, "指定状态无效"),
    THE_STARTING_TIME_CANNOT_BE_LESS_THAN_OR_EQUAL_TO_THE_CURRENT_TIME(1011, "开始时间不能小于等于当前时间"),
    THE_END_TIME_CANNOT_BE_LESS_THAN_OR_EQUAL_TO_THE_START_TIME(1012, "结束时间不能等于小于开始时间"),
    THE_END_TIME_CANNOT_BE_LESS_THAN_OR_EQUAL_TO_THE_CURRENT_TIME(1013, "结束时间不能小于等于当前时间"),
    NOT_A_DIRECTORY(1013, "不是目录"),
    FILE_MERGE_FAILED(1014, "文件合并失败"),
    SHARD_UPLOAD_FAILED(1015, "分片上传失败"),
    THE_PARAMETER_TYPE_IS_INCORRECT(1016, "参数类型不正确"),
    THE_CODE_CANNOT_BE_EMPTY(1017, "CODE不能为空"),
    PARAMETER_CANNOT_BE_EMPTY(1018, "参数不能为空"),
    LACK_OF_PARAMETER(1019, "缺少必要参数，请检查"),
    DATA_RECORD_IS_NOT_EXISTS(1020, "记录不存在,无法执行操作"),
    CODE_ALREADY_EXISTS(1021, "编号已经存在,无法执行操作"),
    REQUEST_PARAMETER_IS_ERROR(1022, "请求参数有误"),
    REQUEST_PARAMETER_IS_NULL(1023, "请求参数不能为空"),
    THE_END_TIME_CANNOT_BE_THE_START_TIME(1024, "结束时间不能等于开始时间"),
    FAILED_TO_UPLOAD_FILES(1025, "文件上传失败"),
    FILE_DOES_NOT_EXIST(1026, "文件不存在"),


    /*-------------------------common end----------------------------------------*/
    /*------------------------- 数据字典 begin code: 3900----------------------------------------*/
    //  3900：数据字典
    DATA_DICTIONARY_LIST_NULL(3901, "数据字典列表为空"),
    DATA_DICTIONARY_CATEGORY_NOT_NULL(3902, "数据字典的类别不能为空"),
    DATA_DICTIONARY_NUMERICAL_NOT_NULL(3903, "数据字典的数值不能为空"),
    DATA_DICTIONARY_MEANING_NOT_NULL(3904, "数据字典的含义不能为空"),
    DATA_DICTIONARY_MEANING_SELECT_FAIL(3905, "数据字典查询失败"),
    /*------------------------- 数据字典 end----------------------------------------*/
    /*------------------------- 认证服务 begin code: 4000----------------------------------------*/

    SUBJECT_UNAUTHORIZED(4000, "无权访问:当前用户没有此请求所需权限"),
    USER_NAME_OR_PASSWORD_ERRORS_GREATER_THAN_5_TIMES(4001, "用户名或密码错误次数大于5次,账户已锁定, 请10分钟后再次访问"),
    ACCOUNT_AUTHORIZATION_EXPIRED(4002, "账号授权过期"),
    ACCOUNT_LOGIN_IS_PROHIBITED(4003, "账号禁止登陆"),
    THE_ACCOUNT_DOES_NOT_EXIST_PLEASE_CHANGE_THE_ACCOUNT_TO_LOGIN(4004, "账号不存在，请更换账号登录"),
    PROHIBIT_THE_LOGIN(4005, "禁止登录"),
    NO_PERMISSIONS(4006, "暂无权限， 请联系管理员"),
    THE_ROLE_IDS_DOES_NOT_EXIST_PLEASE_CHANGE_THE_ACCOUNT_TO_LOGIN(4007, "角色不存在，请联系管理员分配角色"),
    ACCOUNT_AUTOMATIC_LOGOUT(4008, "账号已自动退出登录，无需再次退出登录"),
    THE_ACCOUNT_OR_PASSWORD_IS_INCORRECT(4009, "账号或密码不正确"),


    /*------------------------- 认证服务 end----------------------------------------*/
    /*-------------------------交互服务 begin, code: 5000----------------------------------------*/

    // 流程相关模块
    THE_PROCESS_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED(5601, "流程信息不存在, 或者已删除"),
    THE_PROCESS_ALREADY_EXISTS(5602, "流程信息已存在"),
    THE_FLOW_OPTION_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED(5603, "流操作不存在, 或者已删除"),
    THE_FLOW_OPTION_ALREADY_EXISTS(5604, "流操作信息已存在"),
    THE_PROCESS_STATUS_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED(5605, "流程状态信息不存在, 或者已删除"),
    THE_PROCESS_STATUS_ALREADY_EXISTS(5606, "流程状态信息已存在"),
    THE_QUALIFIER_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED(5607, "流程规则信息不存在, 或者已删除"),
    THE_QUALIFIER_ALREADY_EXISTS(5608, "流程规则信息已存在"),
    THE_REQUEST_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED(5609, "表单信息不存在, 或者已删除"),
    THE_REQUEST_ALREADY_EXISTS(5610, "表单信息已存在"),
    FORM_DUPLICATE_ASSOCIATION(5611, "表单重复关联"),
    THE_PROCESS_CONFIGURATION_INFORMATION_IS_EMPTY(5613, "流程配置信息为空"),
    THE_PROCESS_CONFIGURATION_INFORMATION_IS_NOT_VALID(5614, "流程配置信息不合法"),
    THE_PROCESS_CONFIGURATION_NOT_EXIST_OR_HAS_BEEN_DELETED(5616, "流程配置信息不存在, 或者已删除"),
    A_VALID_PROCESS_CONFIGURATION_WAS_NOT_FOUND(5618, "未找到有效的流程配置, 请先添加流程配置"),
    FLOW_ABNORMAL_CURRENT_NODE_CANNOT_BE_FOUND(5619, "流转异常，找不到当前节点"),
    THE_FLOW_INFORMATION_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED(5621, "流转信息不存在, 或者已删除"),
    ABNORMAL_FLOW_SPECIFIED_RETURN_PERSON_DOES_NOT_EXIST(5623, "流转异常，指定退回人不存在"),
    THE_FLOW_HAS_ENDED_AND_CANNOT_BE_CONTINUED(5624, "流转已结束，不可继续"),
    ABNORMAL_FLOW_SIGNATURE_STATUS_DOES_NOT_EXIST(5625, "流转异常，加签状态不存在"),
    A_LIST_CAN_ONLY_BE_CANCELLED_BY_THE_CREATOR(5626, "单子只允许创建人取消"),
    PROCESS_CONFIGURATION_VERSIONS_ARE_DUPLICATED(5627, "流程配置版本重复"),
    MISSING_START_NODE(5630, "缺少开始节点"),
    MISSING_END_NODE(5630, "缺少结束节点"),
    THE_UNSIGNED_FLOW_RECORD_WAS_NOT_OBTAINED_CORRECTLY(5631, "未正确获取非加签流转记录"),
    PRE_SIGNING_IS_NOT_ALLOWED_DURING_PRE_SIGNING(5632, "前加签过程中不允许后加签"),
    THIS_PARAMETER_CANNOT_BE_CONFIGURED_FOR_MULTIPLE_PROCESSES_SIMULTANEOUSLY(5633, "不可为多个流程同时配置"),

    THE_EXPRESSION_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED(5634, "表达式不存在, 或者已删除"),
    THE_EXPRESSION_ALREADY_EXISTS(5635, "表达式已存在"),

    THE_FORM_TYPE_ALREADY_EXISTS(5636, "表单类型已存在"),
    THE_FORM_TYPE_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED(5637, "表单类型不存在或已删除"),
    THE_PROCESS_CONFIGURATION_VERSION_DOES_NOT_EXIST(5638, "流程配置版本不存在"),
    THE_FORM_POLICY_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED(5679, "表单策略不存在或已删除"),
    NO_VALID_POLICY_WAS_FOUND(5680, "未查找到有效策略"),
    SCRIPT_RUNNING_EXCEPTION(5681, "脚本运行异常"),
    THIS_PROCESS_IS_NOT_SUPPORTED_TO_CONFIGURE_THE_NODE_TYPE(5682, "暂未支持该流程配置节点类型"),
    NO_CONDITIONAL_SUCCESSOR_NODE_IS_CONFIGURED(5683, "未配置条件后继节点"),
    CONDITION_THE_VALUE_OF_THE_SUCCESSOR_NODE_CAN_ONLY_BE_2(6584, "条件后继节点只允许为2"),
    CONDITIONS_ARE_INCORRECTLY_CONFIGURED_ON_THE_CONDITION_NODE(6585, "条件节点未正确配置条件"),
    NO_COUNTERSIGN_NODE_IS_CONFIGURED(6586, "未配置会签节点"),
    THE_MINIMUM_VALUE_OF_THE_COUNTERSIGN_SUCCESSOR_NODE_IS_2(6587, "会签后继节点最小为2"),
    THE_FUNCTIONAL_POLICY_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED(6588, "功能策略不存在或已删除"),
    THE_UPPER_LAYER_INFORMATION_OF_THE_USER_IS_NOT_QUERIED(6589, "未查询到用户上级信息"),




    /*-------------------------交互服务 end----------------------------------------*/
    /*------------------------- 合同服务 begin code: 6000----------------------------------------*/


    /*------------------------- 合同服务 end----------------------------------------*/
    /*------------------------- 权限服务 begin code: 7000----------------------------------------*/
    REGION_ALREADY_EXISTS(7000, "区域已存在"),
    THE_REGION_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED(7001, "区域不存在或已删除"),
    THE_PARENT_REGION_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED(7002, "父级区域不存在或已删除"),
    THE_JOB_TYPE_ALREADY_EXISTS(7003, "职务类型已存在"),
    THE_JOB_TYPE_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED(7004, "职务类型不存在或已删除"),
    THE_JOB_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED(7005, "职务不存在或已删除"),
    POSITION_ALREADY_EXISTS(7006, "职务已存在"),
    JOB_TYPE_AN_ASSOCIATED_JOB_CANNOT_BE_DELETED(7007, "职务类型存在关联职务不可删除"),
    THE_JOB_INFORMATION_ALREADY_EXISTS(7008, "岗位信息已存在"),
    JOB_INFORMATION_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED(7009, "岗位信息不存在或者已删除"),
    POSTS_ASSOCIATED_WITH_POSITIONS_CANNOT_BE_DELETED(7010, "职务存在关联岗位不可删除"),
    A_SUBAREA_ASSOCIATION_CANNOT_BE_DELETED(7011, "有子区域关联不可删除"),
    COMPANY_INFORMATION_ALREADY_EXISTS(7012, "公司信息已存在"),
    COMPANY_INFORMATION_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED(7013, "公司信息不存在或者已删除"),
    THE_PARENT_COMPANY_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED(7014, "父级公司不存在或者已删除"),
    AN_ASSOCIATED_SUBCOMPANY_CANNOT_BE_DELETED(7015, "存在关联子级公司不可删除"),
    DEPARTMENT_ALREADY_EXISTS(7016, "部门已存在"),
    THE_DEPARTMENT_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED(7017, "部门不存在或者已删除"),
    THE_ASSOCIATED_SUB_DEPARTMENTS_CANNOT_BE_DELETED(7018, "存在关联子级部门不可删除"),
    THE_PARENT_DEPARTMENT_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED(7019, "父级部门不存在或已删除"),
    EMPLOYEE_INFORMATION_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED(7020, "员工信息不存在或已删除"),
    EMPLOYEE_INFORMATION_ALREADY_EXISTS(7021, "员工信息已存在"),
    THE_UPPER_LAYER_INFORMATION_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED(7022, "上级信息不存在或已删除"),
    AN_ASSOCIATED_EMPLOYEE_CANNOT_BE_DELETED(7023, "存在关联员工不可删除"),
    IF_AN_ASSOCIATED_DEPARTMENT_EXISTS_IT_CANNOT_BE_DELETED(7024, "存在关联部门不可删除"),
    USER_INFORMATION_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED(7025, "用户信息不存在或已删除"),
    THE_ROLE_TYPE_ALREADY_EXISTS(7026, "角色类型已存在"),
    THE_ROLE_TYPE_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED(7027, "角色类型不存在或已删除"),
    THE_USER_INFORMATION_ALREADY_EXISTS(7028, "用户信息已存在"),
    THE_PASSWORD_CANNOT_BE_EMPTY(7029, "密码不能为空"),
    THE_ROLE_ASSOCIATED_WITH_THE_USER_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED(7030, "用户关联的角色不存在或已被删除"),
    SYSTEM_ADMINISTRATOR_CANNOT_DELETE(7031, "系统管理员不能删除"),
    THE_CURRENT_LOGIN_USER_CANNOT_BE_DELETED(7032, "当前登录用户不可删除"),
    THE_ACCOUNT_CANNOT_BE_EMPTY(7033, "账号不能为空"),
    THE_ACCOUNT_NAME_CANNOT_BE_CHANGED(7034, "帐号名不能更改"),
    PERMISSION_MENU_ALREADY_EXISTS(7035, "权限已存在"),
    THE_PERMISSION_MENU_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED(7036, "权限菜单不存在或已删除"),
    THE_PARENT_PERMISSION_MENU_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED(7037, "父级权限菜单不存在或已删除"),
    ROLE_INFORMATION_ALREADY_EXISTS(7038, "角色信息已存在"),
    ROLE_INFORMATION_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED(7039, "角色信息不存在或已删除"),
    IF_A_ROLE_HAS_ASSOCIATED_USERS_IT_CANNOT_BE_DELETED(7040, "角色存在关联用户不可删除"),
    THE_USER_HAS_AUTHORIZED_THE_ROLE(7041, "用户已授权角色"),
    THE_OLD_PASSWORD_IS_INCORRECT(7042, "原密码不正确"),
    THE_NEW_PASSWORD_IS_THE_SAME_AS_THE_OLD_PASSWORD(7043, "新密码与原密码相同"),
    SYSTEM_ADMINISTRATOR_CANNOT_DISABLE(7044,"系统管理员不能禁用"),
    CURRENT_USER_CANNOT_DISABLE(7045,"当前用户不能禁用, 该用户为当前登录用户"),
    WRONG_ACCOUNT_OR_PASSWORD(7046, "账号或密码错误"),





    /*------------------------- 权限服务 end----------------------------------------*/
    /*------------------------- 采购服务 begin code: 8000----------------------------------------*/


    // 5400：询价单
    INQUIRY_REQUEST_CREATE_FAIL(5401, "询价单创建失败"),
    INQUIRY_REQUEST_SELECT_FAIL(5402, "询价单查询失败"),
    INQUIRY_VENDOR_SELECT_FAIL(5403, "询价单供应商查询失败"),
    INQUIRY_VENDOR_MATERIAL_SELECT_FAIL(5404, "询价单供应商报价查询失败"),
    INQUIRY_VENDOR_MATERIAL_PRICE_NULL(5405, "询价单供应商报价为空"),
    INQUIRY_QUANTITY_ALLOTTED_ERROR(5505, "询价分配数量异常"),
    INQUIRY_QUANTITY_ALLOTTED_PROP_ERROR(5506, "询价分配比例应为100%"),
    INQUIRY_SUBMIT_OFFER_ERROR(5507, "放弃询价,再报价,还价单价三选一"),

    // 5500：招标单
    BID_REQUEST_CREATE_FAIL(5501, "招标单创建失败"),
    BID_REQUEST_SELECT_FAIL(5502, "招标单查询失败"),
    BID_VENDOR_SELECT_FAIL(5503, "招标单供应商查询失败"),
    BID_VENDOR_USER_SELECT_FAIL(5503, "用户未绑定供应商"),
    BID_SPECIALIST_SELECT_FAIL(5504, "招标单专家查询失败"),
    BID_SPECIALIST_GROUP_SELECT_FAIL(5504, "招标单专家组长查询失败"),
    BID_OPE_DATE_FAIL(5505, "还未到招标单开标时间，请稍等！"),
    BID_QUANTITY_ALLOTTED_ERROR(5505, "招标分配数量异常"),
    BID_QUANTITY_ALLOTTED_PROP_ERROR(5506, "招标分配比例应为100%"),

    // 5600：竞价单
    PAID_REQUEST_CREATE_FAIL(5601, "竞价单创建失败"),
    PAID_REQUEST_SELECT_FAIL(5602, "竞价单查询失败"),
    PAID_VENDOR_SELECT_FAIL(5603, "竞价单供应商查询失败"),
    PAID_MATERIAL_SELECT_FAIL(5605, "竞价单物品查询失败"),
    PAID_PRICE_ERROR(5604, "竞价价格异常"),
    PAID_QUANTITY_ALLOTTED_ERROR(5605, "竞价分配数量异常"),
    PAID_QUANTITY_ALLOTTED_PROP_ERROR(5606, "竞价分配比例应为100%"),

    // 5700：表单
    REQUEST_CREATE_FAIL(5701, "表单创建失败"),
    REQUEST_SELECT_FAIL(5702, "表单查询失败"),
    VALUE_CANNOT_BE_EMPTY(5704, "数据不能为空"),

    // 5800：供应商
    SITELNS_SELECT_FAIL(5802, "现场调查查询失败"),
    VENDOR_QUESTION_SELECT_FAIL(5803, "电子调查查询失败"),
    SITELNS_DETAIL_INSERT_FAIL(5804, "现场调查明细新增失败"),
    SITELNS_INSERT_FAIL(5805, "现场调查新增失败"),
    SITELNS_USER_INSERT_FAIL(5806, "现场调查考察人员新增失败"),
    VENDOR_REQUEST_CATEGORY_INSERT_FAIL(5807, "供应商注册单品类新增失败"),
    VENDOR_QUESTION_DETAIL_UPDATE_FAIL(5808, "电子调查明细修改失败"),
    VENDOR_QUALIFY_REVIEW_SELECT_FAIL(5809, "合格评审查询失败"),
    SITELNS_DETAIL_SELECT_FAIL(5810, "现场调查明细查询失败"),
    VENDOR_QUALIFY_REVIEW_RESPONSIBLE_USER_ID_SELECT_FAIL(5811, "合格评审负责人查询失败"),
    VENDOR_QUALIFY_REVIEW_DETAIL_UPDATE_FAIL(5812, "合格评审明细修改失败"),
    VENDOR_QUESTION_UPDATE_FAIL(5813, "电子调查修改失败"),
    SITELNS_ID_CANNOT_BE_EMPTY(5814, "现场考察ID不能为空"),
    SITELNS_USER_SELECT_FAIL(5815, "现场考察人员查询失败"),
    SITELNS_USER_CANNOT_BE_EMPTY(5816, "现场考察人员不能为空"),
    SITELNS_DETAIL_CANNOT_BE_EMPTY(5817, "现场考察明细不能为空"),
    KPI_ID_CANNOT_BE_EMPTY(5818, "绩效ID不能为空"),


    VENDOR_REQUEST_SELECT_FAIL(5901, "供应商注册单查询失败"),
    VENDOR_REQUEST_UPDATE_FAIL(5902, "供应商注册单修改失败"),
    VENDOR_INSERT_FAIL(5903, "供应商新增失败"),
    VENDOR_SELECT_FAIL(5904, "供应商查询失败"),
    VENDOR_FORM_ASSOCIATE_INSERT_FAIL(5905, "供应商各表单关联表新增失败"),
    VENDOR_FORM_ASSOCIATE_SELECT_FAIL(5906, "供应商各表单关联表查询失败"),
    VENDOR_REQUEST_STATUS_JUDGE_FAIL(5907, "供应商注册单状态判断失败"),
    VENDOR_SITE_INS_USER_SELECT_FAIL(5908, "供应商现场考察人员查询失败"),
    VENDOR_SITE_INS_GROUP_USER_SELECT_FAIL(5909, "供应商现场考察组长查询失败"),
    VENDOR_SITE_INS_DETAIL_USER_UPDATE_FAIL(5910, "供应商现场考察人员评价修改失败"),
    VENDOR_CHANGE_SELECT_FAIL(5911, "供应商变更单查询失败"),
    VENDOR_CHANGE_IS_EXIST(5912, "供应商变更单已存在"),
    VENDOR_CHANGE_SITELNS_IS_NOT_ARCHIVE(5913, "供应商变更单下的现场考察单未归档"),
    VENDOR_CHANGE_QUALIFY_REVIEW_IS_NOT_ARCHIVE(5914, "供应商变更单下的合格评审单未归档"),
    VENDOR_CHANGE_KPI_IS_NOT_ARCHIVE(5915, "供应商变更单下的绩效单未归档"),
    VENDOR_CHANGE_UPDATE_FAIL(5916, "供应商变更单修改失败"),
    VENDOR_KPI_USER_SELECT_FAIL(5917, "供应商绩效考核人员查询失败"),
    VENDOR_KPI_GROUP_USER_SELECT_FAIL(5918, "供应商绩效考核组长查询失败"),
    VENDOR_KPI_SELECT_FAIL(5919, "供应商绩效考核组长查询失败"),
    VENDOR_UPDATE_FAIL(5920, "供应商修改失败"),
    VENDOR_KPI_DETAIL_USER_UPDATE_FAIL(5921, "供应商绩效人员评价修改失败"),
    SITE_INS_USER_ID_CANNOT_BE_EMPTY(5922, "现场考察人员ID不能为空"),

    // 6000：
    API_COMPANY_SELECT_FAIL(6001, "API获取公司为空"),
    API_JUDGE_GROUP_COMPANY_FAIL(6001, "API获取公司为空"),
    API_REQUEST_FLOWVO_FAIL(6001, "API获取表单流转失败"),
    API_ROLE_SELECT_FAIL(6004, "API查询角色失败"),
    API_USER_SELECT_FAIL(6005, "API查询用户失败"),

    //合同
    CONTRACT_CLASSIFICATION_DOES_NOT_EXIST(8500, "合同分类不存在,或者已删除"),
    THE_CONTRACT_CLASSIFICATION_ALREADY_EXISTS(8501, "合同分类已存在"),
    THE_CONTRACT_CLASSIFICATION_SERVICE_FIELD_DOES_NOT_EXIST(8503, "合同分类业务字段信息不存在,或者已删除"),
    THE_CONTRACT_CLASSIFICATION_SERVICE_FIELD_ALREADY_EXISTS(8504, "合同分类业务字段信息已存在"),
    THE_CONTRACT_CLASSIFICATION_ATTACHMENT_INFORMATION_ALREADY_EXISTS(8505, "合同分类附件信息已存在"),
    THE_CONTRACT_CLASSIFICATION_ATTACHMENT_INFORMATION_DOES_NOT_EXIST(8506, "合同分类附件信息不存在,或者已删除"),
    THE_CONTRACT_TEMPLATE_INFORMATION_ALREADY_EXISTS(8507, "合同模板信息已存在"),
    THE_CONTRACT_TEMPLATE_INFORMATION_DOES_NOT_EXIST(8508, "合同模板信息不存在,或者已删除"),
    THE_ELECTRONIC_SIGNATURE_INFORMATION_DOES_NOT_EXIST(8509, "电子签章信息不存在,或者已删除"),
    THE_ELECTRONIC_SIGNATURE_INFORMATION_ALREADY_EXISTS(8510, "电子签章信息已存在"),
    THE_TERMINATION_CONDITION_INFORMATION_ALREADY_EXISTS(8510, "合同终止条件信息已存在"),
    TERMINATION_CONDITION_INFORMATION_DOES_NOT_EXIST(8511, "合同终止条件信息不存在,或者已删除"),
    THE_CONTRACT_INFORMATION_DOES_NOT_EXIST(8512, "合同信息不存在,或者已删除"),
    THE_CONTRACT_INFORMATION_ALREADY_EXISTS(8513, "合同信息已存在"),
    CONTRACT_ATTACHMENT_INFORMATION_DOES_NOT_EXIST(8514, "合同附件信息不存在,或者已删除"),
    THE_CONTRACT_BUSINESS_INFORMATION_DOES_NOT_EXIST(8515, "合同业务信息不存在,或者已删除"),
    THE_CONTRACT_OBJECT_INFORMATION_DOES_NOT_EXIST(8516, "合同标的信息不存在,或者已删除"),
    CONTRACT_PHASE_INFORMATION_DOES_NOT_EXIST(8617, "合同阶段信息不存在,或者已删除"),
    CONTRACT_GENERATION_FAILURE(8618, "合同生成失败"),
    THE_CONTRACT_TEMPLATE_FILE_IS_INVALID(8619, "合同模板文件不合法"),
    APPROVAL_PAPER_TO_CREATE_A_FAILURE(8620, "审批单创建失败,请重试"),
    CONTRACT_MAY_CREATE_APPROVAL_PROCESS(8621, "合同未创建审批流程"),
    EXCEPTION_HANDLING_APPROVAL_PROCESS(8622, "审批流程处理异常"),
    APPROVAL_PROCESS_IS_NOT_COMPLETED(8623, "审批过程未完成"),
    CONTRACT_NOT_SUBMITTED(8624, "合同未提交"),

    //项目
    PROJECT_DELETE_FAILD(5901, "项目下存在招标/竞价单，删除失败"),
    PROJECT_SELECT_FAILD(5902, "项目查询失败"),
    PROJECT_TITLE_IS_EXIST(5903, "项目标题已存在"),

    //竞价
    THE_MAIL_TEMPLATE_EXISTS(6004, "邮件模板已存在"),
    THE_MAIL_TEMPLATE_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED(6005, "邮件模板不存在或已删除"),
    THE_EMAIL_TEMPLATE_ADDRESS_CANNOT_BE_EMPTY(6005, "邮件模板地址不能为空"),
    THE_EMAIL_NOTIFICATION_POINT_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED(6006, "邮件通知点不存在或已删除"),
    THE_BULLETIN_BOARD_INFO_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED(6007, "公告板信息不存在或已删除"),
    FAILED_TO_SEND_AN_EMAIL(6008, "邮件发送失败"),

    // 5600：竞价单
    PAID_MATERIAL_CREATE_FAIL(5605, "竞价单物品创建失败"),
    PAID_VENDOR_SELECT_EXIST(5607, "竞价供应商已存在"),
    PAID_FORWARD_PRICE_ERROR(5607, "竞价正向触发价格必须大于起始价格"),
    PAID_INVERSION_PRICE_ERROR(5608, "竞价反向触发价格必须小于起始价格"),
    PAID_VENDOR_PRICE_ERROR(5608, "竞价单中未填写供应商起始价格"),
    PAID_NOT_AUTHORIZATION(5609, "竞价单未授权"),
    PAID_TITLE_IS_EXIST(5603, "竞价单标题已存在"),

    PURCHASE_PLAN_UPDATE_FAIL(6000, "采购计划修改失败"),
    PURCHASE_PLAN_INSERT_FAIL(6001, "采购计划新增失败"),
    SHIPPING_PLAN_SELECT_FAIL(6002, "要货计划物品明细查询失败"),
    SHIPPING_ORDER_ITEM_SELECT_FAIL(6003, "发货单明细查询失败"),
    SHIPPING_ORDER_ITEM_QUANTITY_FAIL(6004, "要货计划物品数量大于采购订单物品数量"),
    SUPPLIER_REGISTRATION_FAILURE(8001, "供应商注册异常, 请重试"),



    /*------------------------- 采购服务 end----------------------------------------*/
    /*------------------------- 供应商服务 begin code: 9000----------------------------------------*/

    SHIPPING_ORDER_INSERT_FAIL(9000, "发货单创建失败"),


    /*------------------------- 供应商服务 end----------------------------------------*/
    /*------------------------- 系统服务 begin code: 10000----------------------------------------*/
    // 单号规则模块
    THE_ORDER_NUMBER_RULE_ALREADY_EXISTS(10000, "单号规则已存在"),

    THE_ORDER_NUMBER_RULE_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED(10001, "单号规则不存在, 或者已删除"),

    THE_ORDER_NUMBER_RULE_CANNOT_BE_MODIFIED(10002, "单号规则不能修改"),

    ABNORMAL_NUMBER_GENERATION(10003, "编号生成异常"),




    /*------------------------- 系统服务 end----------------------------------------*/;

    private final Integer code;
    private final String message;


}
