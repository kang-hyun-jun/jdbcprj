package com.doje.app.console;

import com.doje.app.entity.Notice;
import com.doje.app.service.NoticeService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class NoticeConsole {

    private NoticeService noticeService;
    private int page;
    private int count;

    public NoticeConsole() {
        this.noticeService = new NoticeService();
        this.page = 1;
        this.count = 0;
    }

    public void printNoticeList() throws SQLException, ClassNotFoundException {
        List<Notice> list = noticeService.getlist(page);
        count = noticeService.getCount();

        System.out.printf("─────────────────────────────────────────────\n");
        System.out.printf("<공지사항> 총 %d 게시글\n",count);
        System.out.printf("─────────────────────────────────────────────\n");
        for(Notice notice : list) {
            System.out.printf("%d. %s / %s / %s \n",notice.getId(),notice.getTitle(),notice.getWriter_id(),notice.getDate());
        }
        System.out.printf("─────────────────────────────────────────────\n");
        System.out.printf("              %d/%d pages\n",1,count/10+1);

    }
    public int inputNoticeMenu()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("1.상세조회/ 2.이전/ 3.다음/ 4.글쓰기/ 5.종료 >");
        String menu_string= scanner.nextLine();
        int menu_int = Integer.parseInt(menu_string);
        return menu_int;
    }

    public void movePrevList()
    {
        if(page==1)
        {
            System.out.println("이전 페이지가 존재하지 않습니다.");
            return;
        }
        page --;

    }
    public void moveNextList()
    {
        if(page==count/10+1)
        {
            System.out.println("다음 페이지가 존재하지 않습니다.");
            return;
        }
        page++;
    }
}
