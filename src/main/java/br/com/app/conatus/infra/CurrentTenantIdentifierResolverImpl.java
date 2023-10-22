package br.com.app.conatus.infra;

import java.util.Map;

import org.hibernate.cfg.AvailableSettings;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CurrentTenantIdentifierResolverImpl implements CurrentTenantIdentifierResolver, HibernatePropertiesCustomizer {
   	
	private static final String TENANT_DEFAULT = "UNKNOWN";
	private static ThreadLocal<String> tenant = new ThreadLocal<String>();

	public static void setCurrencyTenant(String tenantRequest) {
		tenant.set(tenantRequest);
	}
	
	public static void clear() {
		tenant.remove();
	}
	
	@Override
    public String resolveCurrentTenantIdentifier() {
		if (tenant.get() == null) {
			return TENANT_DEFAULT;
		}
		
        return tenant.get();
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }

	@Override
	public void customize(Map<String, Object> hibernateProperties) {
		hibernateProperties.put(AvailableSettings.MULTI_TENANT_IDENTIFIER_RESOLVER, this);
	}
}
