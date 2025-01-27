package com.doje.app.console;

import com.doje.app.entity.Notice;
import com.doje.app.service.NoticeService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class NoticeConsole {

    private NoticeService noticeService;
    private int page;
    private String search_string;
    private String search_method;

    public NoticeConsole() {
        this.noticeService = new NoticeService();
        this.page = 1;
        this.search_string = "";
        this.search_method = "TITLE";
    }

    public void printNoticeList() throws SQLException, ClassNotFoundException {
        List<Notice> list = noticeService.getlist(page,search_method,search_string);
        int count = noticeService.getCount();
        int lastPage = (count - 1) / 10 + 1;

        System.out.print("─────────────────────────────────────────────\n");
        System.out.printf("<공지사항> 총 %d 게시글\n",count);
        System.out.print("─────────────────────────────────────────────\n");
        for(Notice notice : list) {
            System.out.printf("%d. %s / %s / %s \n",notice.getId(),notice.getTitle(),notice.getWriter_id(),notice.getDate());
        }
        System.out.print("─────────────────────────────────────────────\n");
        System.out.printf("              %d/%d pages\n",page,lastPage);

    }
    public int inputNoticeMenu()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("1.상세조회/ 2.이전/ 3.다음/ 4.글쓰기/ 5.검색/ 6.모두보기/ 7.종료 >");
        String menu_string= scanner.nextLine();
        int menu_int = Integer.parseInt(menu_string);
        return menu_int;
    }

    public void movePrevList()
    {
        if(page==1)
        {
            System.out.println("===========================");
            System.out.println("이전 페이지가 존재하지 않습니다.");
            System.out.println("===========================");
            return;
        }
        page --;

    }
    public void moveNextList() throws SQLException, ClassNotFoundException {
        int count = noticeService.getCount();
        int lastPage = (count - 1) / 10 + 1;
        if(page==lastPage)
        {
            System.out.println("===========================");
            System.out.println("다음 페이지가 존재하지 않습니다.");
            System.out.println("===========================");
            return;
        }
        page++;
    }
    public void printNoticeAll()
    {
        this.search_string = "";
    }
    public void search()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("─────────────────────────────────────────────\n");
        EXIT: while(true)
        {
            System.out.println("검색할 방식을 입력하세요.");
            System.out.print("1.제목/ 2.작성자/ 3.내용 >");
            String search_method_string = scanner.nextLine();
            int search_method_int = Integer.parseInt(search_method_string);
            switch (search_method_int) {
                case 1:
                    search_method="TITLE";
                    break EXIT;
                case 2:
                    search_method="WRITER_ID";
                    break EXIT;
                case 3:
                    search_method="WRITER_ID";
                    break EXIT;
                default:
                    System.out.println("===========================");
                    System.out.println("올바른 검색 방법을 선택하세요.");
                    System.out.println("===========================");
                    break;
            }
        }
        System.out.print("검색할 내용을 입력하세요. >");
        search_string = scanner.nextLine();
        System.out.print("─────────────────────────────────────────────\n");
    }
}
