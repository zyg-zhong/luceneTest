package com.cms.service.serviceImpl;

import java.util.List;

import com.cms.dao.AlbumChapterDAO;
import com.cms.entity.Album;
import com.cms.entity.Chapter;
import com.cms.service.AlbumChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * albumChapterService.
 *
 * @author allen-zyg.
 * @version 1.0.0
 */
@Service("albumChapterService")
public class AlbumChapterServiceImpl implements AlbumChapterService {
    /**
     * 创建dao层对象.
     */
    @Autowired
    private AlbumChapterDAO acd;

    /**
     * 查所有.
     *
     * @return List<Album>.
     */
    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Album> queryAllAlbums() {
        return acd.selectAllAlbumAndChapter();
    }

    //添加新专辑
    @Override
    @Transactional
    public void addnewAlbum(Album album) {
        acd.insertNewAlbum(album);
    }

    /**
     * 添加专辑文件.
     *
     * @param chapter
     */
    @Override
    @Transactional
    public void addNewChapter(Chapter chapter) {
        acd.insertNewChapter(chapter);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Album queryOneAlbumById(String id) {
        return acd.selectOneAlbum(id);
    }
}
