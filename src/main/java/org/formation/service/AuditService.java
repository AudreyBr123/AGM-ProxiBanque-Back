package org.formation.service;

import java.util.List;
import java.util.Map;

public interface AuditService {
	List<Double> getAllBalances();
	Map<Long, Double> getCriticalBalances();
}
