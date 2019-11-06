package com.hbtcsrzzx.ssm.action;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hbtcsrzzx.ssm.po.InstitutionWithBLOBs;
import com.hbtcsrzzx.ssm.service.InstitutionService;
import com.hbtcsrzzx.utils.ConfigConsts.Constants;
import com.hbtcsrzzx.utils.DateUtils;
import com.hbtcsrzzx.utils.ExcelUtil;

@Controller
@RequestMapping("jsp/files")
public class FileAction {
	private final static int FILE_SIZE = 1024 * 1024 * 20; // 20M
	private final static int IMG_SIZE = 1024 * 1024 * 5; // 5M
	private final static int VIDEO_SIZE = 1024 * 1024 * 100; // 100M
	private final static String FILE_RESOURCE = "C:/file_resource/";
	private final static String FILE_XLS = "C:/file_xls/";

	@RequestMapping("/imgupload")
	@ResponseBody
	public Map<String, Object> imgUpload(MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
		String file_path = "";
		if (!file.isEmpty() && file.getSize() < IMG_SIZE) {
			String fileName = file.getOriginalFilename();
			// String real_path =
			// request.getSession().getServletContext().getRealPath("/file_resource/");
			String newName = UUID.randomUUID().toString() + System.currentTimeMillis()
					+ fileName.substring(fileName.lastIndexOf("."));
			File newFile = new File(FILE_RESOURCE + newName);
			file_path = "/file_resource/" + newName;
			// 将文件写入到存放的地址
			try {
				file.transferTo(newFile);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", 200);
		result.put("msg", "");
		result.put("data", file_path);
		// JSONArray json = JSONArray.fromObject(result);
		return result;
	}

	@RequestMapping("/fileupload")
	@ResponseBody
	public Map<String, Object> fileUpload(@RequestParam(value = "file", required = false) MultipartFile file,
			HttpServletRequest request, HttpServletResponse response) {
		String file_path = "";
		int code = -1;
		if (!file.isEmpty() && file.getSize() < FILE_SIZE) {
			String fileName = file.getOriginalFilename();
			// String path = request.getServletContext().getRealPath("") +
			// "\\file_resource\\";
			String newName = UUID.randomUUID() + fileName.substring(fileName.lastIndexOf("."));
			File newFile = new File(FILE_RESOURCE + newName);
			file_path = "/file_resource/" + newName;
			// 将文件写入到存放的地址
			try {
				file.transferTo(newFile);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			code = 200;
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", code);
		result.put("msg", "");
		result.put("data", file_path);
		// JSONArray json = JSONArray.fromObject(result);
		return result;
	}

	private boolean isImgFile(String fname) {
		boolean res = false;
		if (fname.contains(".png") || fname.contains(".jpg")) {
			res = true;
		}
		return res;
	}

	@RequestMapping("/ueditorimgupload")
	@ResponseBody
	public Map<String, Object> ueditorImgUpload(@RequestParam(value = "upfile", required = false) MultipartFile file,
			HttpServletRequest request, HttpServletResponse response) {
		String file_path = "";
		String state = "上传图片失败";
		if (!file.isEmpty() && file.getSize() < IMG_SIZE) {
			String fileName = file.getOriginalFilename();
			// String path ="file_resource\\";
			// String real_path =
			// request.getSession().getServletContext().getRealPath("/file_resource/");
			String newName = UUID.randomUUID().toString() + System.currentTimeMillis()
					+ fileName.substring(fileName.lastIndexOf("."));
			File newFile = new File(FILE_RESOURCE + newName);
			// 将文件写入到存放的地址
			try {
				file.transferTo(newFile);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			file_path = "/file_resource/" + newName;
			state = "SUCCESS";
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("state", state);// UEDITOR的规则:不为SUCCESS则显示state的内容
		map.put("url", file_path); // 能访问到你现在图片的路径
		map.put("title", "");
		map.put("original", "realName");
		return map;
	}

	@RequestMapping("/ueditorvideoupload")
	@ResponseBody
	public Map<String, Object> ueditorVideoUpload(@RequestParam(value = "upfile", required = false) MultipartFile file,
			HttpServletRequest request, HttpServletResponse response) {
		String file_path = "";
		String state = "上传视频失败";
		if (!file.isEmpty() && file.getSize() < VIDEO_SIZE) {
			String fileName = file.getOriginalFilename();
			// String path ="file_resource\\";
			// String real_path =
			// request.getSession().getServletContext().getRealPath("/file_resource/");
			String newName = UUID.randomUUID().toString() + System.currentTimeMillis()
					+ fileName.substring(fileName.lastIndexOf("."));
			File newFile = new File(FILE_RESOURCE + newName);
			// 将文件写入到存放的地址
			try {
				file.transferTo(newFile);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			file_path = "/file_resource/" + newName;
			state = "SUCCESS";
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("state", state);// UEDITOR的规则:不为SUCCESS则显示state的内容
		map.put("url", file_path); // 能访问到你现在图片的路径
		map.put("title", "");
		map.put("original", "realName");
		return map;
	}

	@RequestMapping("/ueditorfileupload")
	@ResponseBody
	public Map<String, Object> ueditorFileUpload(@RequestParam(value = "upfile", required = false) MultipartFile file,
			HttpServletRequest request, HttpServletResponse response) {
		String file_path = "";
		String state = "上传文件失败";
		if (!file.isEmpty() && file.getSize() < FILE_SIZE) {
			String fileName = file.getOriginalFilename();
			// String path ="file_resource\\";
			// String real_path =
			// request.getSession().getServletContext().getRealPath("/file_resource/");
			String newName = UUID.randomUUID().toString() + System.currentTimeMillis()
					+ fileName.substring(fileName.lastIndexOf("."));
			File newFile = new File(FILE_RESOURCE + newName);

			// 将文件写入到存放的地址
			try {
				file.transferTo(newFile);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			file_path = "/file_resource/" + newName;
			state = "SUCCESS";
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("state", state);// UEDITOR的规则:不为SUCCESS则显示state的内容
		map.put("url", file_path); // 能访问到你现在图片的路径
		map.put("title", "");
		map.put("original", "realName");
		return map;
	}

	@Autowired
	private InstitutionService institutionService;

	@RequestMapping("/fileuploadInstitution")
	@ResponseBody
	public Map<String, Object> fileuploadInstitution(@RequestParam(value = "file", required = false) MultipartFile file,
			String text, HttpServletRequest request, HttpServletResponse response) {
		String file_path = "";
		int code = -1;
		if (!file.isEmpty() && file.getSize() < FILE_SIZE) {
			String fileName = file.getOriginalFilename();
			// String path = request.getServletContext().getRealPath("") +
			// "\\file_resource\\";
			String newName = UUID.randomUUID() + fileName.substring(fileName.lastIndexOf("."));
			File newFile = new File(FILE_XLS + newName);
			file_path = "/file_xls/" + newName;
			// 将文件写入到存放的地址
			try {
				file.transferTo(newFile);
				String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
				if (suffix.equals("xls")) {
					List<List<String[]>> list = ExcelUtil.getExcelData(newFile);
					boolean flag = false;
					for (List<String[]> list2 : list) {
						for (String[] strings : list2) {

							for (int i = 0; i < strings.length; i++) {
								if (strings == null || strings[i].equals("")) {
									flag = true;
									break;
								}
							}
							if (flag) {
								continue;
							}
							flag = false;
							InstitutionWithBLOBs bloBs = new InstitutionWithBLOBs();
							bloBs.setName(strings[0]);
							bloBs.setAddr(strings[1]);
							bloBs.setPhone(strings[4]);
							bloBs.setCategory(strings[2]);
							bloBs.setSubject(strings[3]);
							bloBs.setEmail(strings[5]);
							bloBs.setState(Constants.NORMAL_STATE);
							bloBs.setRegtime(DateUtils.getDateConvertString(new Date(), "yyyy年MM月DD日 HH:mm:ss"));
							bloBs.setIntroduction(strings[6]);
							institutionService.addInstitution(bloBs);
						}
					}

				}

			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			code = 200;
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", code);
		result.put("msg", "");
		result.put("data", file_path);
		// JSONArray json = JSONArray.fromObject(result);
		return result;
	}

}
