package com.music.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.music.entity.Recommend;

import java.io.Serializable;


public class MusicRecommendVO extends Recommend implements Serializable {

    @TableId(value = "details_id", type = IdType.AUTO)
    private Integer detailsId;

    private String url;

    private String albumCover;

    public Integer getDetailsId() {
        return detailsId;
    }

    public void setDetailsId(Integer detailsId) {
        this.detailsId = detailsId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAlbumCover() {
        return albumCover;
    }

    public void setAlbumCover(String albumCover) {
        this.albumCover = albumCover;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHotComment() {
        return hotComment;
    }

    public void setHotComment(String hotComment) {
        this.hotComment = hotComment;
    }

    public String getCommentAuthor() {
        return commentAuthor;
    }

    public void setCommentAuthor(String commentAuthor) {
        this.commentAuthor = commentAuthor;
    }

    private String author;

    private String title;

    private String hotComment;

    private String commentAuthor;

}
