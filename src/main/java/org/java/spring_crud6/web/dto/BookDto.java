package org.java.spring_crud6.web.dto;

import java.util.List;

public class BookDto {

    private String title;
    private int authorId;
    private List<Integer> bookshelfIds;

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public int getAuthorId() {

        return authorId;
    }

    public void setAuthorId(int authorId) {

        this.authorId = authorId;
    }

    public List<Integer> getBookshelfIds() {

        return bookshelfIds;
    }

    public void setBookshelfIds(List<Integer> bookshelfIds) {

        this.bookshelfIds = bookshelfIds;
    }

    @Override
    public String toString() {

        return "CreateBookDto {\n"
                + "\ttitle: " + title + "\n"
                + "\tauthorId: " + authorId + "\n"
                + "\tbookshelfIds: " + bookshelfIds + "\n"
                + "}";
    }
}
