package com.hbtcsrzzx.ssm.action;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hbtcsrzzx.ssm.po.Institution;
import com.hbtcsrzzx.ssm.po.queryVo.Royalty;
import com.hbtcsrzzx.ssm.service.QueryRoyaltyService;
import com.hbtcsrzzx.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("/jsp/queryRoyalty")
public class QueryRoyaltyAction {

    @Autowired
    private QueryRoyaltyService queryRoyaltyService;

    /**
     * 根据用户id,查询对应的提成信息
     *
     * @param id
     * @return
     */
    @RequestMapping("/queryRoyalty")
    @ResponseBody
    public Result findRoyalty(Integer page, Integer limit, Integer id) {

      /*  if (id == -1) {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();


        }*/


        try {

            PageInfo<Royalty> royaltiePageInfo = queryRoyaltyService.findRoyalty(page, limit, id);

            return new Result(200, "获取返利结果成功", royaltiePageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(200, "获取返利结果失败");
        }


    }

}
