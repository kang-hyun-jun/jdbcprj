package com.doje.app.console;

import com.doje.app.entity.Notice;
import com.doje.app.service.NoticeService;

import java.sql.SQLException;
import java.util.List;

public class NoticeConsole {

    private NoticeService noticeService;

    public NoticeConsole() {
        this.noticeService = new NoticeService();
    }

    public void printNoticeList() throws SQLException, ClassNotFoundException {
        List<Notice> list = noticeService.getlist();
        System.out.printf("─────────────────────────────────────────────\n");
        System.out.printf("<공지사항> 총 %d 게시글\n",12);
        System.out.printf("─────────────────────────────────────────────\n");
        for(Notice notice : list) {
            System.out.printf("%d. %s / %s / %s \n",notice.getId(),notice.getTitle(),notice.getWriter_id(),notice.getDate());
        }
        System.out.printf("─────────────────────────────────────────────\n");
        System.out.printf("              %d/%d pages\n",1,2);

    }
    public int inputNoticeMenu()
    {
        return 0;
    }
}
