package org.wso2.custom.generator.jwt;

import java.util.HashMap;
import java.util.Map;

import org.wso2.carbon.apimgt.api.APIManagementException;
import org.wso2.carbon.apimgt.keymgt.service.TokenValidationContext;
import org.wso2.carbon.apimgt.keymgt.token.JWTGenerator;

public class CustomJWTGenerator extends JWTGenerator {

    public Map<String, String> populateStandardClaims(TokenValidationContext validationContext)
            throws APIManagementException {

        Map<String, String> claims = super.populateStandardClaims(validationContext);
        String dialect = getDialectURI();
        String apiContext=claims.get(dialect + "/apicontext");
        String tenant="carbon.super";

        String issuer=dialect.replace("/claims", "");

        if(apiContext.contains("/t/")){
            int indextFromTenant=apiContext.indexOf("t/")+2;
            tenant = apiContext.substring(indextFromTenant);
            tenant = "t/"+tenant.substring(0, tenant.indexOf("/"));
        }

        issuer=issuer+"/"+tenant;
        String apiEndpoint = dialect.replace("/claims", "")+apiContext;

        Map<String, String> nnClaims = new HashMap<String, String>();
        nnClaims.put("sub", (claims.get(dialect + "/enduser")).split("@carbon.super")[0]);
        nnClaims.put("azp", claims.get(dialect + "/applicationid"));
        nnClaims.put("subscriber", claims.get(dialect + "/subscriber"));
        nnClaims.put("applicationname", claims.get(dialect + "/applicationname"));
        nnClaims.put("scope",validationContext.getValidationInfoDTO().getScopes().toString());
        nnClaims.put("iss", issuer);
        nnClaims.put("aud", apiEndpoint);

        return nnClaims;
    }

    public Map<String, String> populateCustomClaims(TokenValidationContext validationContext) throws APIManagementException {

        Map<String, String> claims = super.populateCustomClaims(validationContext);
        Map<String, String> nnClaims = new HashMap<String, String>();
        nnClaims.put("usa", claims.get("http://wso2.org/claims/usa"));

        return nnClaims;
    }
}