package ca.uhn.fhir.jpa.starter.interceptors;

import ca.uhn.fhir.interceptor.api.Hook;
import ca.uhn.fhir.interceptor.api.Interceptor;
import ca.uhn.fhir.interceptor.api.Pointcut;
import ca.uhn.fhir.rest.api.server.RequestDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Interceptor
@Component
public class PPInterceptor {

	private final CustomDataSource dataSource;

	@Autowired
	public PPInterceptor(CustomDataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Hook(Pointcut.SERVER_INCOMING_REQUEST_PRE_HANDLED)
	public void handle(RequestDetails request) {
		String ocCode = request.getHeader("OC-Code");

		try{
			dataSource.setSchema(ocCode);
			System.out.println(dataSource.getConnection());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
