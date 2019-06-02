package com.cms.dao;

import java.util.List;

import com.cms.entity.Album;
import com.cms.entity.Chapter;

public interface AlbumChapterDAO {
	//查找所有
	List<Album> selectAllAlbumAndChapter();
	//添加专辑
	void insertNewAlbum(Album album);
	//上传音频
	void insertNewChapter(Chapter chapter);
	//专辑详情---查一个专辑
	Album selectOneAlbum(String id);
	
}
