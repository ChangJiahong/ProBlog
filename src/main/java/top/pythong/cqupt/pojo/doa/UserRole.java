package top.pythong.cqupt.pojo.doa;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "user_role")
public class UserRole implements Serializable {
    /**
     * ��ɫid
     */
    @Id
    @Column(name = "r_id")
    private Integer rId;

    /**
     * �û�id
     */
    @Id
    @Column(name = "u_id")
    private Integer uId;

    /**
     * ����ʱ��
     */
    private Date created;

    private static final long serialVersionUID = 1L;

    /**
     * ��ȡ��ɫid
     *
     * @return r_id - ��ɫid
     */
    public Integer getrId() {
        return rId;
    }

    /**
     * ���ý�ɫid
     *
     * @param rId ��ɫid
     */
    public void setrId(Integer rId) {
        this.rId = rId;
    }

    /**
     * ��ȡ�û�id
     *
     * @return u_id - �û�id
     */
    public Integer getuId() {
        return uId;
    }

    /**
     * �����û�id
     *
     * @param uId �û�id
     */
    public void setuId(Integer uId) {
        this.uId = uId;
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