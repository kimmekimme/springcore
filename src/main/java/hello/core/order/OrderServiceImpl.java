package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.w3c.dom.Node;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;//회원찾기
    private final DiscountPolicy discountPolicy;//널포인트익셉션 어떻게?

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy(); //할인찾기
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); //OCP 위반



    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        //ctrl alt v 추출

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}