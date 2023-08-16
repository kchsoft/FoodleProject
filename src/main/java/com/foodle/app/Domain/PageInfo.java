package com.foodle.app.Domain;

import org.springframework.web.util.UriComponentsBuilder;

public class PageInfo {
    private int page;
    private int pageBundle; // 10, 20, 30
    private int totalPageCnt;
    private int firstPageNavi;
    private int lastPageNavi;
    private int underPageNaviSize;
    private boolean next;
    private boolean prev;

    public PageInfo(){}

    public PageInfo(int totalPageCnt, int page, int pageBundle) {
        this.totalPageCnt = totalPageCnt;
        this.page = page;
        setPageBundle(pageBundle);
        calPageInfo();
    }

    public void calPageInfo() {
        this.underPageNaviSize = 10;
        this.firstPageNavi = ((page - 1) / underPageNaviSize) * underPageNaviSize + 1;
        this.lastPageNavi = Math.min(firstPageNavi + underPageNaviSize - 1, (totalPageCnt - 1) / pageBundle + 1);

        this.prev = page <= 1 ? false : true;
        this.next = page >= lastPageNavi ? false : true;
    }


    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageBundle() {
        return pageBundle;
    }

    public void setPageBundle(int pageBundle) {
        this.pageBundle = (pageBundle == 20 || pageBundle == 30) ? pageBundle : 10;
    }

    public int getTotalPageCnt() {
        return totalPageCnt;
    }

    public void setTotalPageCnt(int totalPageCnt) {
        this.totalPageCnt = totalPageCnt;
    }

    public int getFirstPageNavi() {
        return firstPageNavi;
    }

    public void setFirstPageNavi(int firstPageNavi) {
        this.firstPageNavi = firstPageNavi;
    }

    public int getLastPageNavi() {
        return lastPageNavi;
    }

    public void setLastPageNavi(int lastPageNavi) {
        this.lastPageNavi = lastPageNavi;
    }

    public int getUnderPageNaviSize() {
        return underPageNaviSize;
    }

    public void setUnderPageNaviSize(int underPageNaviSize) {
        this.underPageNaviSize = underPageNaviSize;
    }

    public boolean isNext() {
        return next;
    }

    public void setNext(boolean next) {
        this.next = next;
    }

    public boolean isPrev() {
        return prev;
    }

    public void setPrev(boolean prev) {
        this.prev = prev;
    }

    @Override
    public String toString() {
        return "PageInfo{" +
                "page=" + page +
                ", pageBundle=" + pageBundle +
                ", totalPageCnt=" + totalPageCnt +
                ", firstPageNavi=" + firstPageNavi +
                ", lastPageNavi=" + lastPageNavi +
                ", next=" + next +
                ", prev=" + prev +
                '}';
    }

    public int getOffset() {
        return (page - 1) * pageBundle;
    }


    public String getPageQuery(int page, int pageBundle) {
        return UriComponentsBuilder.newInstance()
                .queryParam("page", page)
                .queryParam("pageBundle", pageBundle)
                .build().toString();
    }

}
