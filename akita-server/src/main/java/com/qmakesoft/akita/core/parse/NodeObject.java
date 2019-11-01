package com.qmakesoft.akita.core.parse;

import java.util.List;

/**
 * 
 * @author Jerry.Zhao
 *
 */
public class NodeObject {
	
	/**
	 * 节点的类型，设计时考虑用户扩展的类型
	 */
	String type;
	
	String code;
	
	/**
	 * 宽度|数字
	 */
	Integer width; 
	
	/**
	 *  高度|数字,
	 */
	Integer height;
	
	Integer xPosition;
	
	Integer yPosition;
	
	/**
	 * 事件,执行过节点后去触发的事件
	 */
	List<String> enterEvents;
	
	/**
	 * 事件,执行过节点后去触发的事件
	 */
	List<String> leaveEvents;
	
	//////////////////////////单任务节点//////////////////////////////////////
	
	String participantType;
	
	/**
	 * 标记该节点是否一个可以接受驳回的点
	 */
	Boolean rejectPoint;
	/**
	 * 驳回时能够选择的驳回节点列表
	 */
	List<String> rejectPoints;
	/**
	 * 标记该节点是否能够做驳回操作
	 */
	Boolean rejectAble;
	/**
	 * 单步执行/直接跳转
	 */
	String rejectType;
	/**
	 * 任务标志
	 */
	List<String> workFlags;
	/**
	 * 系统分配，参与者抢收，参与者抢办
	 */
	String competitionType;
	/**
	 * 当为系统分配时，选择竞争算法，默认为随机
	 */
	String competitionHandler;
	/**
	 * 在这个节点是否可以转办操作
	 */
	Boolean transferAble;
	
	////////////////////////////以下适用普通任务和会签任务///////////////////
	
	/**
	 * 是否可以加签支持协助
	 */
	Boolean supportAble;
	/**
	 * 必选下拉|根据扩展接口获取内容
	 */
	String findParticipantHandler;
	/**
	 * 必选下拉,没有找到处理人时|交管理员处理/提示错误/跳过此节点
	 */
	String noParticipantHandler;
	/**
	 * 必选下拉,处理人重复处理时|跳过此节点/不做处理
	 */
	String repeatParticipantHandler;
	/**
	 * 期限|正整数
	 */
	Integer deadline;
	/**
	 * 描述期限的计量单位 （默认以分钟为单位）| 分钟/小时/天
	 */
	String deadlineDateUnit;
	/**
	 * 达到期限后处理方式|跳过此节点/不做处理
	 */
	String timeoutHandler;
	
	//进入该节点后设置过多久提醒
	
	/**
	 * 接收任务后提醒first , true/false
	 */
	Boolean remindFirst;
	/**
	 * 任务创建后多长时间触发首次的提醒 | 正整数
	 */
	Integer remindFirstTime;
	/**
	 * 描述remindFirstTime的计量单位（默认以分钟为单位）| 分钟/小时/天
	 */
	String remindFirstDateUnit;
	/**
	 * 即将超时提醒last,true/false
	 */
	Boolean remindLast;
	/**
	 * 任务即将到期前多长时间触发最后的提醒 | 正整数
	 */
	Integer remindLastTime;
	/**
	 * 描述remindLastTime的计量单位（默认以分钟为单位）| 分钟/小时/天
	 */
	String remindLastDateUnit;
	/**
	 * 通知处理人处理器|短信/邮件/其他
	 */
	String noticeHandler;
	/**
	 * 要通知的人
	 */
	String noticeFindParticipantHandler;
	/**
	 * 当进入到该节点时提醒发起人 | true,false
	 */
	Boolean noticeOnFirst;
	/**
	 * 处理结束后是否通知发起人| true,false
	 */
	Boolean noticeOnProcessed;
	/**
	 * 当节点超时通知发起人| true,false
	 */
	Boolean noticeOnTimeout;
	
	///////////////////////////会签任务//////////////////////////////////////////
	/**
	 * 下拉必须|一票通过,一票否决，投票模式(少数服从多数)，人工判断
	 */
	String countersignHandler;
	/**
	 * 顺序执行|true/false
	 */
	Boolean order;
	
	///////////////////////////程序节点//////////////////////////////////////////
	/**
	 * 间隔时间执行|正整数，0表示立即执行
	 */
	Integer executeIntervalTime;
	/**
	 * 描述intervalTime的计量单位
	 */
	String executeIntervalDateUnit;
	/**
	 * 程序的处理器//可以去实现如代码拉取，编译等程序动作
	 */
	String applicationHandler;
	/**
	 * 程序执行时的上下文参数
	 */
	String contextJsonParams;
	/**
	 * 出错处理|跳过此节点/设置流程异常等待管理员处理
	 */
	String errorHandler;
	/**
	 * 重试次数|正整数
	 */
	Integer retryTimes;
	/**
	 * 重试间隔时长 |正整数，0表示立即
	 */
	Integer retryIntervalTime;
	/**
	 * 重试间隔时长的计量单位
	 */
	String retryIntervalDateUnit;
	/////////////////////////////决策判断//////////////////////////////////////////
	/**
	 * Groovy脚本|Handler应用程序
	 */
	String decisionType;
	/**
	 * 脚本内容
	 */
	String groovyScript;
	/**
	 * 应用程序内容
	 */
	String decisionHandler;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getxPosition() {
		return xPosition;
	}

	public void setxPosition(Integer xPosition) {
		this.xPosition = xPosition;
	}

	public Integer getyPosition() {
		return yPosition;
	}

	public void setyPosition(Integer yPosition) {
		this.yPosition = yPosition;
	}

	public List<String> getEnterEvents() {
		return enterEvents;
	}

	public void setEnterEvents(List<String> enterEvents) {
		this.enterEvents = enterEvents;
	}

	public List<String> getLeaveEvents() {
		return leaveEvents;
	}

	public void setLeaveEvents(List<String> leaveEvents) {
		this.leaveEvents = leaveEvents;
	}

	public String getParticipantType() {
		return participantType;
	}

	public void setParticipantType(String participantType) {
		this.participantType = participantType;
	}

	public Boolean getRejectPoint() {
		return rejectPoint;
	}

	public void setRejectPoint(Boolean rejectPoint) {
		this.rejectPoint = rejectPoint;
	}

	public List<String> getRejectPoints() {
		return rejectPoints;
	}

	public void setRejectPoints(List<String> rejectPoints) {
		this.rejectPoints = rejectPoints;
	}

	public Boolean getRejectAble() {
		return rejectAble;
	}

	public void setRejectAble(Boolean rejectAble) {
		this.rejectAble = rejectAble;
	}

	public String getRejectType() {
		return rejectType;
	}

	public void setRejectType(String rejectType) {
		this.rejectType = rejectType;
	}

	public List<String> getWorkFlags() {
		return workFlags;
	}

	public void setWorkFlags(List<String> workFlags) {
		this.workFlags = workFlags;
	}

	public String getCompetitionType() {
		return competitionType;
	}

	public void setCompetitionType(String competitionType) {
		this.competitionType = competitionType;
	}

	public String getCompetitionHandler() {
		return competitionHandler;
	}

	public void setCompetitionHandler(String competitionHandler) {
		this.competitionHandler = competitionHandler;
	}

	public Boolean getTransferAble() {
		return transferAble;
	}

	public void setTransferAble(Boolean transferAble) {
		this.transferAble = transferAble;
	}

	public Boolean getSupportAble() {
		return supportAble;
	}

	public void setSupportAble(Boolean supportAble) {
		this.supportAble = supportAble;
	}

	public String getFindParticipantHandler() {
		return findParticipantHandler;
	}

	public void setFindParticipantHandler(String findParticipantHandler) {
		this.findParticipantHandler = findParticipantHandler;
	}

	public String getNoParticipantHandler() {
		return noParticipantHandler;
	}

	public void setNoParticipantHandler(String noParticipantHandler) {
		this.noParticipantHandler = noParticipantHandler;
	}

	public String getRepeatParticipantHandler() {
		return repeatParticipantHandler;
	}

	public void setRepeatParticipantHandler(String repeatParticipantHandler) {
		this.repeatParticipantHandler = repeatParticipantHandler;
	}

	public Integer getDeadline() {
		return deadline;
	}

	public void setDeadline(Integer deadline) {
		this.deadline = deadline;
	}

	public String getDeadlineDateUnit() {
		return deadlineDateUnit;
	}

	public void setDeadlineDateUnit(String deadlineDateUnit) {
		this.deadlineDateUnit = deadlineDateUnit;
	}

	public String getTimeoutHandler() {
		return timeoutHandler;
	}

	public void setTimeoutHandler(String timeoutHandler) {
		this.timeoutHandler = timeoutHandler;
	}

	public Boolean getRemindFirst() {
		return remindFirst;
	}

	public void setRemindFirst(Boolean remindFirst) {
		this.remindFirst = remindFirst;
	}

	public Integer getRemindFirstTime() {
		return remindFirstTime;
	}

	public void setRemindFirstTime(Integer remindFirstTime) {
		this.remindFirstTime = remindFirstTime;
	}

	public String getRemindFirstDateUnit() {
		return remindFirstDateUnit;
	}

	public void setRemindFirstDateUnit(String remindFirstDateUnit) {
		this.remindFirstDateUnit = remindFirstDateUnit;
	}

	public Boolean getRemindLast() {
		return remindLast;
	}

	public void setRemindLast(Boolean remindLast) {
		this.remindLast = remindLast;
	}

	public Integer getRemindLastTime() {
		return remindLastTime;
	}

	public void setRemindLastTime(Integer remindLastTime) {
		this.remindLastTime = remindLastTime;
	}

	public String getRemindLastDateUnit() {
		return remindLastDateUnit;
	}

	public void setRemindLastDateUnit(String remindLastDateUnit) {
		this.remindLastDateUnit = remindLastDateUnit;
	}

	public String getNoticeHandler() {
		return noticeHandler;
	}

	public void setNoticeHandler(String noticeHandler) {
		this.noticeHandler = noticeHandler;
	}

	public String getNoticeFindParticipantHandler() {
		return noticeFindParticipantHandler;
	}

	public void setNoticeFindParticipantHandler(String noticeFindParticipantHandler) {
		this.noticeFindParticipantHandler = noticeFindParticipantHandler;
	}

	public Boolean getNoticeOnFirst() {
		return noticeOnFirst;
	}

	public void setNoticeOnFirst(Boolean noticeOnFirst) {
		this.noticeOnFirst = noticeOnFirst;
	}

	public Boolean getNoticeOnProcessed() {
		return noticeOnProcessed;
	}

	public void setNoticeOnProcessed(Boolean noticeOnProcessed) {
		this.noticeOnProcessed = noticeOnProcessed;
	}

	public Boolean getNoticeOnTimeout() {
		return noticeOnTimeout;
	}

	public void setNoticeOnTimeout(Boolean noticeOnTimeout) {
		this.noticeOnTimeout = noticeOnTimeout;
	}

	public String getCountersignHandler() {
		return countersignHandler;
	}

	public void setCountersignHandler(String countersignHandler) {
		this.countersignHandler = countersignHandler;
	}

	public Boolean getOrder() {
		return order;
	}

	public void setOrder(Boolean order) {
		this.order = order;
	}

	public Integer getExecuteIntervalTime() {
		return executeIntervalTime;
	}

	public void setExecuteIntervalTime(Integer executeIntervalTime) {
		this.executeIntervalTime = executeIntervalTime;
	}

	public String getExecuteIntervalDateUnit() {
		return executeIntervalDateUnit;
	}

	public void setExecuteIntervalDateUnit(String executeIntervalDateUnit) {
		this.executeIntervalDateUnit = executeIntervalDateUnit;
	}

	public String getApplicationHandler() {
		return applicationHandler;
	}

	public void setApplicationHandler(String applicationHandler) {
		this.applicationHandler = applicationHandler;
	}

	public String getContextJsonParams() {
		return contextJsonParams;
	}

	public void setContextJsonParams(String contextJsonParams) {
		this.contextJsonParams = contextJsonParams;
	}

	public String getErrorHandler() {
		return errorHandler;
	}

	public void setErrorHandler(String errorHandler) {
		this.errorHandler = errorHandler;
	}

	public Integer getRetryTimes() {
		return retryTimes;
	}

	public void setRetryTimes(Integer retryTimes) {
		this.retryTimes = retryTimes;
	}

	public Integer getRetryIntervalTime() {
		return retryIntervalTime;
	}

	public void setRetryIntervalTime(Integer retryIntervalTime) {
		this.retryIntervalTime = retryIntervalTime;
	}

	public String getRetryIntervalDateUnit() {
		return retryIntervalDateUnit;
	}

	public void setRetryIntervalDateUnit(String retryIntervalDateUnit) {
		this.retryIntervalDateUnit = retryIntervalDateUnit;
	}

	public String getDecisionType() {
		return decisionType;
	}

	public void setDecisionType(String decisionType) {
		this.decisionType = decisionType;
	}

	public String getGroovyScript() {
		return groovyScript;
	}

	public void setGroovyScript(String groovyScript) {
		this.groovyScript = groovyScript;
	}

	public String getDecisionHandler() {
		return decisionHandler;
	}

	public void setDecisionHandler(String decisionHandler) {
		this.decisionHandler = decisionHandler;
	}
	
}
