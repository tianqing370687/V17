package com.xiongmengyingshi.v17.controller;

import com.xiongmengyingshi.v17.controller.vo.GetPersonalInfoByIdVO;
import com.xiongmengyingshi.v17.controller.vo.ListPersonalInfoByPageVO;
import com.xiongmengyingshi.v17.entity.PersonalInfo;
import com.xiongmengyingshi.v17.service.PersonalInfoService;
import com.xiongmengyingshi.v17.utils.POIExcelUtils;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by nicholas.chi on 2018/1/15.
 */
//@RequestMapping(value = "/crm")
@Controller
public class CrmPersonalInfoController {

    @Autowired
    private PersonalInfoService personalInfoService;

    private Logger logger = LogManager.getLogger(CrmPersonalInfoController.class);

    @ResponseBody
    @ApiOperation(value = "分页查询信息", notes = "")
    @RequestMapping(value = "/listPersonalInfoByPage",method = RequestMethod.POST)
    public ListPersonalInfoByPageVO listPersonalInfoByPage(int currPage, int pageSize){
        List<PersonalInfo> list = personalInfoService.listPersonalInfoByPage(currPage,pageSize);
        ListPersonalInfoByPageVO vo = new ListPersonalInfoByPageVO();
        vo.setCurrPage(currPage);
        vo.setPageSize(pageSize);
        vo.setList(list);
        return vo;
    }

    @ResponseBody
    @ApiOperation(value = "根据ID获取信息", notes = "")
    @RequestMapping(value = "/getPersonalInfoById",method = RequestMethod.POST)
    public GetPersonalInfoByIdVO getPersonalInfoById(int infoId){
        GetPersonalInfoByIdVO vo = new GetPersonalInfoByIdVO();
        PersonalInfo personalInfo = personalInfoService.getPersonlInfoById(infoId);
        vo.setPersonalInfo(personalInfo);
        return vo;
    }

    @ResponseBody
    @ApiOperation(value = "导出excel", notes = "")
    @RequestMapping(value = "/excelExport",method = RequestMethod.GET)
    public void excelExport(HttpServletResponse response){

        POIExcelUtils utils = new POIExcelUtils(response,"report","sheet1");
        String titleColumn[] = {
                "infoId", "name", "birthday", "age", "birthplace",
                "residence", "performingExperience", "familyComposition", "occupation", "schoolsMajors",
                "grade", "placeOfStudy", "phoneNum", "qq", "email",
                "weibo", "homePhone",  "specialty", "interest", "awards",
                "dream", "idol", "comic", "filmWorks", "website",
                "app", "wantToSay", "video1Url", "video2Url", "mugShotImgUrl",
                "halfLengthImgUrl", "fullBodyImgUrl", "serialNum", "createTime",
        };

        String titleName[] = {
                "编号", "姓名", "生日", "年龄",
                "出生地", "现住地", "演艺经历",  "家庭构成",
                "职业", "学校与专业", "年级",  "就读地/工作地",
                "手机", "QQ", "Email", "微博",
                "家庭电话", "特长", "兴趣", "获奖情况",
                "梦想", "喜欢的艺人、偶像或配音演员", "喜欢的动漫、游戏作品", "喜欢的影视作品",
                "常去的网站", "常用的手机APP", "想对你未来可能开始的声优/声优偶像生涯说点什么", "配音DEMON/声线展示视频地址",
                "其他才艺展示视频地址", "大头贴", "半身照", "全身照",
                "序列号", "报名时间",
        };

        int[] titleSize = {
                5,20,20,20,20,20,20,20,20,20,
                20,20,20,20,20,20,20,20,20,20,
                20,20,20,20,20,20,20,20,20,20,
                20,20,20,20,20
        };

        List<PersonalInfo> list = personalInfoService.listPersonalInfoByAll();

        utils.wirteExcel(titleColumn,titleName,titleSize,list);

        //http://www.voidcn.com/article/p-rgftaukw-boa.html

    }


}
