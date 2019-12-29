package com.chuncongcong.productmgmt.page;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.springframework.util.Assert;

/**
 * @author HU
 * @date 2019/12/27 15:55
 */

public class SimplePagingObject<T> implements PagingObject<T> {

    // 页码
    private int pageNum;

    // 分页大小
    private int pageSize;

    // 总页数
    private int totalPages;

    // 总记录数
    private long totalElements;

    // 分页内容
    private List<T> content;

    public SimplePagingObject(List<T> content, int pageNum, int pageSize, long totalElements) {
        Assert.isTrue(pageNum > 0, "pageNum must be positive.");
        Assert.isTrue(pageSize > 0, "pageSize must be positive.");
        Assert.isTrue(totalElements >= 0, "totalElements must net be negative.");
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
        this.totalPages = (int)(totalElements / pageSize + (totalElements % pageSize == 0 ? 0 : 1));
        this.content = content == null ? Collections.EMPTY_LIST : content;
    }

    protected void setContent(List<T> content) {
        this.content = content;
    }

    /**
     * 页码
     *
     * @return
     */
    @Override
    public int getPageNum() {
        return pageNum;
    }

    /**
     * 分页大小
     *
     * @return
     */
	@Override
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 总页数
     *
     * @return
     */
	@Override
    public int getTotalPages() {
        return totalPages;
    }

    /**
     * 总记录数
     *
     * @return
     */
	@Override
    public long getTotalElements() {
        return totalElements;
    }

    /**
     * 分页内容记录数
     *
     * @return
     */
	@Override
    public int getNumberOfElements() {
        return content.size();
    }

    /**
     * 是否有上一页
     *
     * @return
     */
	@Override
    public boolean hasPrevious() {
        return pageNum > 1;
    }

    /**
     * 是否有下一页
     *
     * @return
     */
	@Override
    public boolean hasNext() {
        return pageNum < totalPages;
    }

    /**
     * 是否是第一页
     *
     * @return
     */
	@Override
    public boolean isFirst() {
        return !hasPrevious();
    }

    /**
     * 是否是最后一页
     *
     * @return
     */
	@Override
    public boolean isLast() {
        return !hasNext();
    }

    /**
     * 是否有分页内容
     *
     * @return
     */
	@Override
    public boolean hasContent() {
        return !content.isEmpty();
    }

	@Override
    public List<T> getContent() {
        return Collections.unmodifiableList(content);
    }

    @Override
    public Iterator<T> iterator() {
        return content.iterator();
    }
}
