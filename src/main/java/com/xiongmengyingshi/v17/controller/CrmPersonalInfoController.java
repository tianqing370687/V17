package com.xiongmengyingshi.v17.controller;

import com.xiongmengyingshi.v17.constant.ErrCodeConstant;
import com.xiongmengyingshi.v17.controller.vo.GetPersonalInfoByIdVO;
import com.xiongmengyingshi.v17.controller.vo.ListPersonalInfoByPageVO;
import com.xiongmengyingshi.v17.entity.PersonalInfo;
import com.xiongmengyingshi.v17.service.PersonalInfoService;
import com.xiongmengyingshi.v17.utils.POIExcelUtils;
import com.xiongmengyingshi.v17.utils.exportexcel.ExcelException;
import com.xiongmengyingshi.v17.utils.exportexcel.ExcelUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by nicholas.chi on 2018/1/15.
 */
@RequestMapping(value = "/crm")
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
        vo.setRetCode(ErrCodeConstant.GET_LIST_SUCCESS);
        return vo;
    }

    @ResponseBody
    @ApiOperation(value = "根据ID获取信息", notes = "")
    @RequestMapping(value = "/getPersonalInfoById",method = RequestMethod.POST)
    public GetPersonalInfoByIdVO getPersonalInfoById(int infoId){
        GetPersonalInfoByIdVO vo = new GetPersonalInfoByIdVO();
        PersonalInfo personalInfo = personalInfoService.getPersonlInfoById(infoId);
        vo.setPersonalInfo(personalInfo);
        vo.setRetCode(ErrCodeConstant.GET_LIST_SUCCESS);
        return vo;
    }

    @ResponseBody
    @ApiOperation(value = "根据ID删除信息", notes = "")
    @RequestMapping(value = "/deletePersonInfoById",method = RequestMethod.POST)
    public String deletePersonInfoById(int infoId){
        int result = personalInfoService.deletePersonalInfobyId(infoId);
        if(result > 0){
            return ErrCodeConstant.DELETE_PERSONAL_INFO_SUCCESS;
        }else {
            return ErrCodeConstant.DELETE_PERSONAL_INFO_FAIL;
        }
    }

//    @ResponseBody
//    @ApiOperation(value = "导出excel", notes = "")
//    @RequestMapping(value = "/excelExport",method = RequestMethod.GET)
//    public void excelExport(HttpServletResponse response){
//
//        POIExcelUtils utils = new POIExcelUtils(response,"report","sheet1");
//        String titleColumn[] = {
//                "infoId", "name", "birthday", "age", "birthplace",
//                "residence", "performingExperience", "familyComposition", "occupation", "schoolsMajors",
//                "grade", "placeOfStudy", "phoneNum", "qq", "email",
//                "weibo", "homePhone",  "specialty", "interest", "awards",
//                "dream", "idol", "comic", "filmWorks", "website",
//                "app", "wantToSay", "video1Url", "video2Url", "mugShotImgUrl",
//                "halfLengthImgUrl", "fullBodyImgUrl", "serialNum", "createTime",
//        };
//
//        String titleName[] = {
//                "编号", "姓名", "生日", "年龄",
//                "出生地", "现住地", "演艺经历",  "家庭构成",
//                "职业", "学校与专业", "年级",  "就读地/工作地",
//                "手机", "QQ", "Email", "微博",
//                "家庭电话", "特长", "兴趣", "获奖情况",
//                "梦想", "喜欢的艺人、偶像或配音演员", "喜欢的动漫、游戏作品", "喜欢的影视作品",
//                "常去的网站", "常用的手机APP", "想对你未来可能开始的声优/声优偶像生涯说点什么", "配音DEMON/声线展示视频地址",
//                "其他才艺展示视频地址", "大头贴", "半身照", "全身照",
//                "序列号", "报名时间",
//        };
//
//        int[] titleSize = {
//                5,20,20,20,20,20,20,20,20,20,
//                20,20,20,20,20,20,20,20,20,20,
//                20,20,20,20,20,20,20,20,20,20,
//                20,20,20,20,20
//        };
//
//        List<PersonalInfo> list = personalInfoService.listPersonalInfoByAll();
//
//        for (PersonalInfo info:list) {
//            logger.info(info.toString());
//        }
//
//        utils.wirteExcel(titleColumn,titleName,titleSize,list);
//
//        //http://www.voidcn.com/article/p-rgftaukw-boa.html
//    }

    @ResponseBody
    @ApiOperation(value = "导出excel", notes = "")
    @RequestMapping(value = "/excelExport",method = RequestMethod.GET)
    public void excelExport(HttpServletResponse response){

        List<PersonalInfo> list = personalInfoService.listPersonalInfoByAll();

        for (PersonalInfo info:list) {
            logger.info(info.toString());
        }

        LinkedHashMap<String,String> fieldMap = new LinkedHashMap<String, String>();
        fieldMap.put("infoId", "编号");
        fieldMap.put("name", "姓名");
        fieldMap.put("birthday", "生日");
        fieldMap.put("age", "年龄");
        fieldMap.put("birthplace", "出生地");

        fieldMap.put("residence", "现住地");
        fieldMap.put("performingExperience", "演艺经历");
        fieldMap.put("familyComposition", "家庭构成");
        fieldMap.put("occupation", "职业");
        fieldMap.put("schoolsMajors", "学校与专业");

        fieldMap.put("grade", "年级");
        fieldMap.put("placeOfStudy", "就读地/工作地");
        fieldMap.put("phoneNum", "手机");
        fieldMap.put("qq", "QQ");
        fieldMap.put("email", "Email");

        fieldMap.put("weibo", "微博");
        fieldMap.put("homePhone", "家庭电话");
        fieldMap.put("specialty", "特长");
        fieldMap.put("interest", "兴趣");
        fieldMap.put("awards", "获奖情况");

        fieldMap.put("dream", "梦想");
        fieldMap.put("idol", "喜欢的艺人、偶像或配音演员");
        fieldMap.put("comic", "喜欢的动漫、游戏作品");
        fieldMap.put("filmWorks", "喜欢的影视作品");
        fieldMap.put("website", "常去的网站");

        fieldMap.put("app", "常用的手机APP");
        fieldMap.put("wantToSay", "想对你未来可能开始的声优/声优偶像生涯说点什么");
        fieldMap.put("video1Url", "配音DEMON/声线展示视频地址");
        fieldMap.put("video2Url", "其他才艺展示视频地址");
        fieldMap.put("mugShotImgUrl", "大头贴");

        fieldMap.put("halfLengthImgUrl", "半身照");
        fieldMap.put("fullBodyImgUrl", "全身照");
        fieldMap.put("serialNum", "序列号");
        fieldMap.put("createTime", "报名时间");

        String sheetName = "page1";
        String fileName = "v17_personInfo_"+(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        int sheetSize = 65535;

        try {
            ExcelUtil.listToExcel(list,fieldMap,sheetName,fileName,sheetSize,response);
        } catch (ExcelException e) {
            logger.info("export excel error!");
            e.printStackTrace();

        }
    }


}
