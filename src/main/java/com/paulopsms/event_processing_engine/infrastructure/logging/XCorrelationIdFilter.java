package com.paulopsms.event_processing_engine.infrastructure.logging;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Component
public class XCorrelationIdFilter extends OncePerRequestFilter {

	private static final String HEADER = "X-Correlation-Id";

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

		String correlationId = request.getHeader(HEADER);

		if (correlationId == null || correlationId.isEmpty()) {
			correlationId = UUID.randomUUID().toString();
		}

		MDC.put("correlationId", correlationId);

		response.setHeader(HEADER, correlationId);

		filterChain.doFilter(request, response);

		MDC.clear();
	}
}
