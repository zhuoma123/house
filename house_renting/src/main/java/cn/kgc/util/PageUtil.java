package cn.kgc.util;

/**
 * @Version 1.0
 * @Author:dama zhuo
 * @Date:2019/12/21 10:54
 * @Content:
 */
//pageUtil封装分页的参数类  针对esayUI
public class PageUtil {
    private int page;//取名叫page，和前端传来的参数名一致
    private  int rows;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
}
