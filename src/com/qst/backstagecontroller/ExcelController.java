package com.qst.backstagecontroller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qst.entity.Opus;
import com.qst.entity.Order;
import com.qst.entity.User;
import com.qst.service.OpusService;
import com.qst.service.UserService;


@Controller
@RequestMapping("/backstage")
public class ExcelController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private OpusService opusService;
	
	
	@RequestMapping("/downloadUser.form")
	public void downloadUser(HttpServletRequest request,HttpServletResponse response) throws Exception{
		List<User> users = userService.getUserAll();
		request.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		HSSFWorkbook wb = new HSSFWorkbook();
		
		HSSFSheet sheet = wb.createSheet("用户信息表");
		
		HSSFRow row = sheet.createRow((int) 0);
		
		HSSFCellStyle style = wb.createCellStyle();
		
		style.setAlignment(HorizontalAlignment.CENTER);
		
		String[] titles = {"ID","用户名","手机号码","用户性别","用户地址","用户介绍","用户邮箱","用户状态"};
		HSSFCell cell;
		for (int x = 0; x < titles.length; x++) {
			cell = row.createCell(x);
			cell.setCellValue(titles[x]);
			cell.setCellStyle(style);
		}
		// 生成excel格式后要将数据写入excel：
		// 循环将数据写入Excel
		
		for (int i = 0; i < users.size(); i++) {
			row = sheet.createRow((int) i + 1);
			User user = users.get(i);
			row.createCell(0).setCellValue(user.getId()==null?0:user.getId());
			row.createCell(1).setCellValue(user.getName()==null?"":user.getName());
			row.createCell(2).setCellValue(user.getTel()==null?"":user.getTel());
			row.createCell(3).setCellValue(user.getSex()==null?"":user.getSex());
			row.createCell(4).setCellValue(user.getAddress()==null?"":user.getAddress());
			row.createCell(5).setCellValue(user.getSynopsis()==null?"":user.getSynopsis());
			row.createCell(6).setCellValue(user.getEmail()==null?"":user.getEmail());
			int status = user.getStatus();
			if(status == 0){
				row.createCell(7).setCellValue("普通用户");
			}else if(status == 1){
				row.createCell(7).setCellValue("管理员");
			}else if(status == 2){
				row.createCell(7).setCellValue("客服");
			}else if(status == 3){
				row.createCell(7).setCellValue("禁用");
			}else if(status == 4){
				row.createCell(7).setCellValue("删除");
			}
		}
		FileOutputStream out = new FileOutputStream("E:/用户信息.xls");
		wb.write(out);
		out.close();
		// 弹出下载框
		String fileName = "用户信息表";
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		wb.write(os);
		byte[] content = os.toByteArray();
		InputStream is = new ByteArrayInputStream(content);
		// 设置response参数，可以打开下载页面
		response.reset();//清除首部的空白行
		// 设置编码格式
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		response.setHeader("Content-Disposition",
				"attachment;filename=" + new String((fileName + ".xls").getBytes(), "iso-8859-1"));
		ServletOutputStream out1 = response.getOutputStream();
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			bis = new BufferedInputStream(is);
			bos = new BufferedOutputStream(out1);
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}
	}
	
	@RequestMapping("/downloadOpus.form")
	public void downloadOpus(HttpServletRequest request,HttpServletResponse response) throws Exception{
		List<Opus> opuss = opusService.getOpusAll();
		request.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		HSSFWorkbook wb = new HSSFWorkbook();
		
		HSSFSheet sheet = wb.createSheet("作品信息表");
		
		HSSFRow row = sheet.createRow((int) 0);
		
		HSSFCellStyle style = wb.createCellStyle();
		
		style.setAlignment(HorizontalAlignment.CENTER);
		
		String[] titles = {"ID","作品标题","作者","简介","创建时间","点赞数","热度","售卖价格","类型"
				,"题材","状态"};
		HSSFCell cell;
		for (int x = 0; x < titles.length; x++) {
			cell = row.createCell(x);
			cell.setCellValue(titles[x]);
			cell.setCellStyle(style);
		}
		// 生成excel格式后要将数据写入excel：
		// 循环将数据写入Excel
		
		for (int i = 0; i < opuss.size(); i++) {
			row = sheet.createRow((int) i + 1);
			Opus opus = opuss.get(i);
			row.createCell(0).setCellValue(opus.getId()==null?0:opus.getId());
			row.createCell(1).setCellValue(opus.getOpus_name()==null?"":opus.getOpus_name());
			row.createCell(2).setCellValue(opus.getAuthor_name()==null?"":opus.getAuthor_name());
			row.createCell(3).setCellValue(opus.getOpus_synopsis()==null?"":opus.getOpus_synopsis());
			row.createCell(4).setCellValue(opus.getOpus_create_time()==null?"":opus.getOpus_create_time());
			row.createCell(5).setCellValue(opus.getOpus_like_times()==null?0:opus.getOpus_like_times());
			row.createCell(6).setCellValue(opus.getOpus_heat()==null?0:opus.getOpus_heat());
			row.createCell(7).setCellValue(opus.getOpus_price()== 0?0:opus.getOpus_price());
			row.createCell(8).setCellValue(opus.getOpus_type()==null?"":opus.getOpus_type());
			row.createCell(9).setCellValue(opus.getOpus_tipic()==null?"":opus.getOpus_tipic());
			int status = opus.getStatus();
			if(status == 0){
				row.createCell(10).setCellValue("上架");
			}else if(status == 1){
				row.createCell(10).setCellValue("下架");
			}else if(status == 2){
				row.createCell(10).setCellValue("删除");
			}else if(status == 4){
				row.createCell(10).setCellValue("已出售");
			}
		}
		FileOutputStream out = new FileOutputStream("E:/作品信息.xls");
		wb.write(out);
		out.close();
		// 弹出下载框
		String fileName = "作品信息表";
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		wb.write(os);
		byte[] content = os.toByteArray();
		InputStream is = new ByteArrayInputStream(content);
		// 设置response参数，可以打开下载页面
		response.reset();//清除首部的空白行
		// 设置编码格式
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		response.setHeader("Content-Disposition",
				"attachment;filename=" + new String((fileName + ".xls").getBytes(), "iso-8859-1"));
		ServletOutputStream out1 = response.getOutputStream();
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			bis = new BufferedInputStream(is);
			bos = new BufferedOutputStream(out1);
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}
	}
	
	@RequestMapping("/downloadOder.form")
	public void downloadOder(HttpServletRequest request,HttpServletResponse response) throws Exception{
		List<Order> orders = opusService.seekOrderAll();
		request.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		HSSFWorkbook wb = new HSSFWorkbook();
		
		HSSFSheet sheet = wb.createSheet("订单信息表");
		
		HSSFRow row = sheet.createRow((int) 0);
		
		HSSFCellStyle style = wb.createCellStyle();
		
		style.setAlignment(HorizontalAlignment.CENTER);
		
		String[] titles = {"ID","订单号","订单类型","用户名称","作品名称","收获地址","售卖价格","创建时间","状态"};
		HSSFCell cell;
		for (int x = 0; x < titles.length; x++) {
			cell = row.createCell(x);
			cell.setCellValue(titles[x]);
			cell.setCellStyle(style);
		}
		// 生成excel格式后要将数据写入excel：
		// 循环将数据写入Excel
		
		for (int i = 0; i < orders.size(); i++) {
			row = sheet.createRow((int) i + 1);
			Order order = orders.get(i);
			row.createCell(0).setCellValue(order.getId()==null?0:order.getId());
			row.createCell(1).setCellValue(order.getOrder_number()==null?"":order.getOrder_number());
			row.createCell(2).setCellValue(order.getOrder_type()==null?"":order.getOrder_type());
			row.createCell(3).setCellValue(order.getUser_name()==null?"":order.getUser_name());
			row.createCell(4).setCellValue(order.getOpus_name()==null?"":order.getOpus_name());
			row.createCell(5).setCellValue(order.getUser_address()==null?"":order.getUser_address());
			row.createCell(6).setCellValue(order.getOpus_price()==0?0:order.getOpus_price());
			row.createCell(7).setCellValue(order.getOrder_date()== null?"":order.getOrder_date());
			row.createCell(8).setCellValue(order.getStatus()== null?"":order.getStatus());
		}
		FileOutputStream out = new FileOutputStream("E:/订单信息.xls");
		wb.write(out);
		out.close();
		// 弹出下载框
		String fileName = "订单信息表";
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		wb.write(os);
		byte[] content = os.toByteArray();
		InputStream is = new ByteArrayInputStream(content);
		// 设置response参数，可以打开下载页面
		response.reset();//清除首部的空白行
		// 设置编码格式
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		response.setHeader("Content-Disposition",
				"attachment;filename=" + new String((fileName + ".xls").getBytes(), "iso-8859-1"));
		ServletOutputStream out1 = response.getOutputStream();
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			bis = new BufferedInputStream(is);
			bos = new BufferedOutputStream(out1);
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}
	}
}
