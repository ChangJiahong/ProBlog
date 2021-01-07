package top.pythong.cqupt.pojo.doa;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

public class Role implements Serializable {
    /**
     * ��������
     */
    @Id
    private Integer rid;

    /**
     * ��ɫ��
     */
    private String rname;

    /**
     * ����ʱ��
     */
    private Date created;

    private static final long serialVersionUID = 1L;

    /**
     * ��ȡ��������
     *
     * @return rid - ��������
     */
    public Integer getRid() {
        return rid;
    }

    /**
     * ������������
     *
     * @param rid ��������
     */
    public void setRid(Integer rid) {
        this.rid = rid;
    }

    /**
     * ��ȡ��ɫ��
     *
     * @return rname - ��ɫ��
     */
    public String getRname() {
        return rname;
    }

    /**
     * ���ý�ɫ��
     *
     * @param rname ��ɫ��
     */
    public void setRname(String rname) {
        this.rname = rname == null ? null : rname.trim();
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
}