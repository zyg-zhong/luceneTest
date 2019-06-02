package com.cms.service;

import java.util.List;

import com.cms.entity.Album;
import com.cms.entity.Chapter;

/**
 * @author allen-zyg.
 * @version 1.0.0
 */
public interface AlbumChapterService {
    /**
     * 查询所有专辑和音频.
     * @return List<Album>
     */
    List<Album> queryAllAlbums();
    /**添加专辑.
     * @param album album.
     * */
    void addnewAlbum(Album album);

    /**
     *  上传音频.
     * @param chapter .
     */

    void addNewChapter(Chapter chapter);

    /**
     * 查看专辑详情.
     * @param id .
     * @return Album
     */
    Album queryOneAlbumById(String id);

}
