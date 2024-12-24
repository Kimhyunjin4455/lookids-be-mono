package lookids.mono.common.jwt;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.mono.auth.service.AuthUserDetailService;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	//    todo JwtTokenProvider
	private final JwtTokenProvider jwtTokenProvider;
	private final AuthUserDetailService userDetailsService;

	//private final AuthCustomerDetailService userDetailsService;
	// private final TokenBlacklistRepository tokenBlacklistRepository;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws ServletException, IOException {

		final String authHeader = request.getHeader("Authorization");
		final String jwt;
		//final String uuid;
		log.info("Authorization : {}", authHeader);

		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}

		jwt = authHeader.substring(7);
		log.info("생성된 jwt : {}", jwt); // todo jwt 확인

		// // 블랙리스트에 있는 토큰인지 확인
		// if (tokenBlacklistRepository.isBlacklisted(jwt)) {
		// 	log.warn("블랙리스트에 있는 토큰: {}", jwt);
		// 	response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "블랙리스트에 있는 토큰입니다.");
		// 	return;
		// }

		// 토큰 검증이나 사용자 정보 추출 등의 로직은 게이트웨이에서 처리하므로 주석 처리
		// uuid = Jwts.parser().verifyWith((SecretKey) jwtTokenProvider.getSignKey())
		// 	.build().parseSignedClaims(jwt).getPayload().get("uuid", String.class);
		//
		// log.info("uuid: {}", uuid);

		// if (SecurityContextHolder.getContext().getAuthentication() == null) {
		// 	UserDetails userDetails = this.userDetailsService.loadUserByUsername(uuid);
		// 	UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
		// 		userDetails,
		// 		null,
		// 		userDetails.getAuthorities()
		// 	);
		// 	authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		// 	SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		// }
		filterChain.doFilter(request, response);
	}
}