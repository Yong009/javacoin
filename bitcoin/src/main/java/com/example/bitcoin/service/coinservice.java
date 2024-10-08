package com.example.bitcoin.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.example.bitcoin.dto.BoardVO;
import com.example.bitcoin.dto.CommentVO;
import com.example.bitcoin.dto.MemberVO;
import com.example.bitcoin.dto.NoticeVO;
import com.example.bitcoin.dto.OrderVO;
import com.example.bitcoin.dto.PagingVO;
import com.example.bitcoin.dto.PriceVO;
import com.example.bitcoin.dto.ProfitVO;
import com.example.bitcoin.dto.QuestionVO;


public interface coinservice {

	// 코드 가져오기
	public List<MemberVO> getCode(String id);
	// 코드 저장
	public boolean saveCode(MemberVO vo);
	// 게시판 전체 리스트
	public List<BoardVO> getList();
	// 페이징
	public List<BoardVO> getLists(PagingVO vo);
	// 잔고 조회
    String account7(MemberVO vo);
	// 현재가격 조회
	String currentPrice7();
	// 마켓 정보
	String market7();
	// 매수
	String order7(OrderVO vo) throws NoSuchAlgorithmException, UnsupportedEncodingException;
	// 매도
	String sell7(OrderVO vo) throws NoSuchAlgorithmException, UnsupportedEncodingException;
	//자동매매 끄기
	public void autoStop7(String id);
	// 자동매매 켜기
	void autoOn(String id);
	// 자동매매 모니터링
	List<MemberVO> getMemberAuto(String auto);
	// 회원 정보 수정
	void updateMember(MemberVO vo);
	// 전체 멤버
	List<MemberVO> getMemberAll();
	// 회원탈퇴
	void deleteMember(String id);
	// 회원가입
	void memberJoin(MemberVO member);
	// 아디 중복 체크
	int checkId(MemberVO member);
	// 게시판 글 등록
	public void insertBoard(BoardVO vo);
	// 게시판 상세 내용
	public List<BoardVO> getListDetail(Long seq);
	// 게시판 내용 수정
	public void updateBoard(BoardVO vo);
	// 조회수 증가
	public void updateView(BoardVO vo);
	// 게시판 글 삭제
	public void deleteBoard(BoardVO vo);
	// 댓글 불러오기
	public List<CommentVO> getComment(CommentVO vo);
	// 댓글 삭제
	public void deleteComment(CommentVO vo);
	// 댓글 등록
	public void insertComment(CommentVO vo);
	// 건의 사항 들고오기
	public List<QuestionVO> getQuestion();
	// 건의 사항 개인 꺼 들고오기
	public List<QuestionVO> getQuestion2(String writer);
	// 건의 사항 디테일
	public List<QuestionVO> getQuestionDetail(Long seq);
	// 변동성 스케줄러
	public List<MemberVO> autoCheck();
	/*public void join(MemberVO vo);*/
	// 관리자가 자동매매 강제 종료
	public void manageAuto(String id);
	// 게시판 글 수
	public int getMax();
	// 자동 매매할 금액 저장
	public void saveAutoPrice(MemberVO vo);
	// 자동 매매중 매수 금액
	public void saveOrderPrice(MemberVO vo);

	// 자동 매매 전일 고가 , 저가 저장
	public void updatePrice(PriceVO vo);

	// 거래량 50위 상위
	public String rsiSearch();

	// 자동 매매 전일 고가, 저가 가져오기
	public List<PriceVO> getPriceList();
	// 총 회원 수
	public int getMemberMax();

	// 회원 관리 페이징
	public List<MemberVO> getMemberLists(PagingVO vo);

	// 팔떄 가격 저장
	public void updateSellPrice(MemberVO vo);

	// 수익률 저장
	public void saveProfit(ProfitVO vo);

	// 수익률 불러오기
	public List<ProfitVO> getProfitList(ProfitVO vo);

	// 공지사항 게시판
	public List<NoticeVO> getNotice();

	// 건의 사항 게시판 총 수
	public int getQuestionMax();

	// 자동매매 돌리는 사람
	public int autoOnMax();

	// 자동매매 안돌리는 사람
	public int autoOffMax();

	// 모니터 페이징1
	public List<MemberVO> getMoniterOn(PagingVO vo);

	// 모니터 페이징2
	public List<MemberVO> getMoniterOff(PagingVO vo);

	// 건의 사항 총 수
	public int noticeMax();

	// 건의 사항 전체 페이징
	public List<QuestionVO> questionList2(PagingVO vo);

	// 건의 사항 그 사람 페이징
	public List<QuestionVO> questionList3(PagingVO vo);

	// rsi 돌리는 사람들
	public List<MemberVO> getRsiMember();
	// rsi에 필요한 정보 넣기
	public void insertRsi(MemberVO vo);

}
