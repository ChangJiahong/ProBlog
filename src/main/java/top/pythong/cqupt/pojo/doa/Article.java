package top.pythong.cqupt.pojo.doa;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

public class Article implements Serializable {
    /**
     * ����id
     */
    @Id
    private Integer id;

    /**
     * ���±���
     */
    private String title;

    /**
     * ���±���
     */
    private String alias;

    /**
     * ����
     */
    private String author;

    /**
     * ������Ϣ
     */
    private String info;

    /**
     * ��������
     */
    private Integer hits;

    /**
     * ����޸�ʱ��
     */
    private Date modified;

    /**
     * ����ʱ��
     */
    private Date created;

    /**
     * ����״̬
     */
    private String status;

    /**
     * �����ͼ
     */
    @Column(name = "front_cover_img_url")
    private String frontCoverImgUrl;

    /**
     * ��������
     */
    private String content;

    private static final long serialVersionUID = 1L;

    /**
     * ��ȡ����id
     *
     * @return id - ����id
     */
    public Integer getId() {
        return id;
    }

    /**
     * ��������id
     *
     * @param id ����id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * ��ȡ���±���
     *
     * @return title - ���±���
     */
    public String getTitle() {
        return title;
    }

    /**
     * �������±���
     *
     * @param title ���±���
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * ��ȡ���±���
     *
     * @return alias - ���±���
     */
    public String getAlias() {
        return alias;
    }

    /**
     * �������±���
     *
     * @param alias ���±���
     */
    public void setAlias(String alias) {
        this.alias = alias == null ? null : alias.trim();
    }

    /**
     * ��ȡ����
     *
     * @return author - ����
     */
    public String getAuthor() {
        return author;
    }

    /**
     * ��������
     *
     * @param author ����
     */
    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    /**
     * ��ȡ������Ϣ
     *
     * @return info - ������Ϣ
     */
    public String getInfo() {
        return info;
    }

    /**
     * ����������Ϣ
     *
     * @param info ������Ϣ
     */
    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    /**
     * ��ȡ��������
     *
     * @return hits - ��������
     */
    public Integer getHits() {
        return hits;
    }

    /**
     * ���õ�������
     *
     * @param hits ��������
     */
    public void setHits(Integer hits) {
        this.hits = hits;
    }

    /**
     * ��ȡ����޸�ʱ��
     *
     * @return modified - ����޸�ʱ��
     */
    public Date getModified() {
        return modified;
    }

    /**
     * ��������޸�ʱ��
     *
     * @param modified ����޸�ʱ��
     */
    public void setModified(Date modified) {
        this.modified = modified;
    }

    /**
     * ��ȡ����ʱ��
     *
     * @return created - ����ʱ��
     */
    public Date getCreated() {
        return created;
    }

    /**
     * ���ô���ʱ��
     *
     * @param created ����ʱ��
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * ��ȡ����״̬
     *
     * @return status - ����״̬
     */
    public String getStatus() {
        return status;
    }

    /**
     * ��������״̬
     *
     * @param status ����״̬
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * ��ȡ�����ͼ
     *
     * @return front_cover_img_url - �����ͼ
     */
    public String getFrontCoverImgUrl() {
        return frontCoverImgUrl;
    }

    /**
     * ���÷����ͼ
     *
     * @param frontCoverImgUrl �����ͼ
     */
    public void setFrontCoverImgUrl(String frontCoverImgUrl) {
        this.frontCoverImgUrl = frontCoverImgUrl == null ? null : frontCoverImgUrl.trim();
    }

    /**
     * ��ȡ��������
     *
     * @return content - ��������
     */
    public String getContent() {
        return content;
    }

    /**
     * ������������
     *
     * @param content ��������
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}