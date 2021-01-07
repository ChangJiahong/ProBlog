package top.pythong.cqupt.pojo.doa;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

public class Article implements Serializable {
    /**
     * 文章id
     */
    @Id
    private Integer id;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章别名
     */
    private String alias;

    /**
     * 作者
     */
    private String author;

    /**
     * 描述信息
     */
    private String info;

    /**
     * 单击次数
     */
    private Integer hits;

    /**
     * 最近修改时间
     */
    private Date modified;

    /**
     * 创建时间
     */
    private Date created;

    /**
     * 文章状态
     */
    private String status;

    /**
     * 封面大图
     */
    @Column(name = "front_cover_img_url")
    private String frontCoverImgUrl;

    /**
     * 文章内容
     */
    private String content;

    private static final long serialVersionUID = 1L;

    /**
     * 获取文章id
     *
     * @return id - 文章id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置文章id
     *
     * @param id 文章id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取文章标题
     *
     * @return title - 文章标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置文章标题
     *
     * @param title 文章标题
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 获取文章别名
     *
     * @return alias - 文章别名
     */
    public String getAlias() {
        return alias;
    }

    /**
     * 设置文章别名
     *
     * @param alias 文章别名
     */
    public void setAlias(String alias) {
        this.alias = alias == null ? null : alias.trim();
    }

    /**
     * 获取作者
     *
     * @return author - 作者
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 设置作者
     *
     * @param author 作者
     */
    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    /**
     * 获取描述信息
     *
     * @return info - 描述信息
     */
    public String getInfo() {
        return info;
    }

    /**
     * 设置描述信息
     *
     * @param info 描述信息
     */
    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    /**
     * 获取单击次数
     *
     * @return hits - 单击次数
     */
    public Integer getHits() {
        return hits;
    }

    /**
     * 设置单击次数
     *
     * @param hits 单击次数
     */
    public void setHits(Integer hits) {
        this.hits = hits;
    }

    /**
     * 获取最近修改时间
     *
     * @return modified - 最近修改时间
     */
    public Date getModified() {
        return modified;
    }

    /**
     * 设置最近修改时间
     *
     * @param modified 最近修改时间
     */
    public void setModified(Date modified) {
        this.modified = modified;
    }

    /**
     * 获取创建时间
     *
     * @return created - 创建时间
     */
    public Date getCreated() {
        return created;
    }

    /**
     * 设置创建时间
     *
     * @param created 创建时间
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * 获取文章状态
     *
     * @return status - 文章状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置文章状态
     *
     * @param status 文章状态
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 获取封面大图
     *
     * @return front_cover_img_url - 封面大图
     */
    public String getFrontCoverImgUrl() {
        return frontCoverImgUrl;
    }

    /**
     * 设置封面大图
     *
     * @param frontCoverImgUrl 封面大图
     */
    public void setFrontCoverImgUrl(String frontCoverImgUrl) {
        this.frontCoverImgUrl = frontCoverImgUrl == null ? null : frontCoverImgUrl.trim();
    }

    /**
     * 获取文章内容
     *
     * @return content - 文章内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置文章内容
     *
     * @param content 文章内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}