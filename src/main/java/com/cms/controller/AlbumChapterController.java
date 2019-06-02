package com.cms.controller;

import com.cms.entity.Album;
import com.cms.entity.Chapter;
import com.cms.service.AlbumChapterService;
import com.cms.util.UUIDUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * album
 *
 * @author allen-zyg.
 * @version 1.0.0
 */
@Controller
@RequestMapping("/album")
public class AlbumChapterController {

    /**
     * 创建service对象.
     */
    @Autowired
    private AlbumChapterService acs;

    /**
     * 查所有.
     *
     * @return List<Album>
     */
    @RequestMapping("/queryAllAlbums")
    @ResponseBody
    public List<Album> queryAllAlbums() {
        return acs.queryAllAlbums();
    }

    /**
     * 添加新专辑.
     *
     * @param file2 .
     * @param album .
     * @throws IllegalStateException .
     * @throws IOException           .
     */
    @RequestMapping("/appendNewAlbum")
    @ResponseBody
    public void appendNewAlbum(MultipartFile file2, Album album) throws IllegalStateException, IOException {
        System.out.println("请求到了");
        //uuid---id
        String albumid = UUIDUtil.getid();
        String id1 = albumid.replace(":", "");
        String id2 = id1.replace("+", "");
        String uid = id2.replace(" ", "");
        album.setId(uid);
        //uuid插图名称
        String albumimg = UUIDUtil.getid() + file2.getOriginalFilename();
        //去空格
        String num1 = albumimg.replace(":", "");
        String num2 = num1.replace("+", "");
        String imgname = num2.replace(" ", "");
        File f = new File("E:\\apache-tomcat-7\\apache-tomcat-7.0.79\\webapps\\cmfz_zyg\\img\\album\\" + imgname);
        file2.transferTo(f);
        //设置图片名称
        album.setCoverPath(imgname);
        //调用业务
        acs.addnewAlbum(album);
    }

    /**
     * 专辑详情
     *
     * @param id .
     * @return .
     */
    @RequestMapping("/queryOneAlbum")
    @ResponseBody
    public Album queryOneAlbum(String id) {
        System.out.println("id:" + id);
        System.out.println(acs.queryOneAlbumById(id));
        return acs.queryOneAlbumById(id);

    }

    /**
     * 上传音频.
     * @param file3 .
     * @param chapter .
     * @throws IllegalStateException .
     * @throws IOException .
     */
    @RequestMapping("/appendNewChapter")
    @ResponseBody
    public void appendNewChapter(MultipartFile file3, Chapter chapter) throws IllegalStateException, IOException {
        System.out.println("音频" + file3);
        System.out.println("请求到了");
        //uuid---id
        String chapterid = UUIDUtil.getid();
        String id1 = chapterid.replace(":", "");
        String id2 = id1.replace("+", "");
        String uid = id2.replace(" ", "");
        chapter.setId(uid);
        //uuid音频名称
        String chapterimg = UUIDUtil.getid() + file3.getOriginalFilename();
        //去空格
        String num1 = chapterimg.replace(":", "");
        String num2 = num1.replace("+", "");
        String imgname = num2.replace(" ", "");
        File f = new File("E:\\apache-tomcat-7\\apache-tomcat-7.0.79\\webapps\\cmfz_zyg\\img\\upload\\" + imgname);
        file3.transferTo(f);
        //设置图片----src
        chapter.setSrc(imgname);
        //调用业务
        acs.addNewChapter(chapter);
    }

    /**
     * 下载音频.
     *
     * @param fileName .
     * @param session  .
     * @param response .
     * @throws IOException .
     */
    @RequestMapping("/download")
    @ResponseBody
    public void download(String fileName, HttpSession session, HttpServletResponse response) throws IOException {
        //获取要下载的文件
        //真实地址
        String realPath = session.getServletContext().getRealPath("/img/upload");
        //字节
        byte[] bb = FileUtils.readFileToByteArray(new File(realPath + "/" + fileName)); //手动导包import org.apache.commons.io.FileUtils;
        //设置响应头格式（以附件形式下载）
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
        //获取输出流---从程序中输出到客户端 ------输出
        ServletOutputStream out = response.getOutputStream();
        out.write(bb);

        //关流
        if (out != null) {
            out.flush();
        }
        if (out != null) {
            out.close();
        }


    }
}
