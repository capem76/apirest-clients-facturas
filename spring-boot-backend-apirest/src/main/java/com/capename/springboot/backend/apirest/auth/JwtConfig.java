package com.capename.springboot.backend.apirest.auth;

public class JwtConfig {
	
	public static final String LLAVE_SECRETA = "alguna.clave.secreta.importante.12345678";
	
	public static final String RSA_PRIVATE = "-----BEGIN RSA PRIVATE KEY-----\n"
			+ "MIIEowIBAAKCAQEA4wPx8zXyONfJ4Zkjkz6b0GKVNt/LNTCI/SNdfRBs2ltqZW5P\n"
			+ "B8Z+j8MEsAtvKQM+sG/p+RqW7bhUpsrfMoBEpKOnWHrwt0JQIfDioPvSqqXbfEpb\n"
			+ "dphA6bHusm1+uA7WJH3S2DoHFIHIpwhSMqEe67B7fBilTqqU2mv5ya/o5tfz/hp6\n"
			+ "vlEoDu07mgIJ22zAss74LH+EGPQ0pKPWnF2FpVDpVV/3peROFIpUHn4GvM85Lqfr\n"
			+ "7hhnf8OKuuBwNcQ72z9XE1OEbZKpCE4SXWMbINhJh9OZbwEXtLNHfIUBLgjAISkf\n"
			+ "OUGF9OPoCvFLLC0/HQ00WUc3KPRUG5LM1uTlDwIDAQABAoIBAFSCanactYA9rJTE\n"
			+ "Gn89Bmor2Npls1Mor0Z0/N+K1sruzwvVZaxCU2kXM06J4ybEY3jxIIuwP1Fdln3E\n"
			+ "9hPptiQIHEsO8T93ezc3YlaFiGYoei2pzKS4hym4KGDZ7Bt+wm8xO+VsrKgQrG6p\n"
			+ "5VedrVIWD9IhpqBDVkh9e65+Qth0BovS36XwVAi/OUnhgdJX3yoiiVWr81gLSxQ5\n"
			+ "BJf8JBgoHtZPnv9GxLLYCCixWxq1ljTt0t0yUi8D/qWn+Wy3Yr6EFAwTh+OodMQ7\n"
			+ "cYIg+Yv/zBsAzGN0+yMQKrVlcUy81LEVgauPiEKwEgm1/xBMCDKUVBrDQz/zg409\n"
			+ "mme7HHkCgYEA89zQll2WF0Zd/dJon2sTFdtcIMiOrWB0Hr+k0axW3ty1YrulBfWN\n"
			+ "RyOzGj28kxKe370MtoC9nKQWvoulgqFvmgIcjpHC6uQbyRHyFjLjhKtp25ByShXK\n"
			+ "vIcwy6nVaS35rhRJ6SK2W+a/A/otbaw197c+mtZwhbNn5p+aGI3Wy10CgYEA7lB4\n"
			+ "v9JnFEGADrThug/hRlazlC4Q9TwTuNjKq9HZwt7ZwqiQC4t9nEhVwHRraRuqF1T1\n"
			+ "tru8cpjfxB50qQKiz7mbIhLTPeNoU7RVpmED83WTHMB7M+q3V25oAxKz43xTw6hD\n"
			+ "Fk3EQlLOtBfmaS5MFDIKAR5yhqfVfF21MgVGV1sCgYBc2oVp27ScpCSB0y4XFXQs\n"
			+ "7FuF+BermIBefOZLF+3z6eH++Cf7vuXOYp99rGgzL/Hii19d1mNcU3Z9kzqwJLwT\n"
			+ "zh4FgybOvZB+3SBqp3HOQF6MRdHet7F8W5WO1tn1tJXPXNZPuPHqyE+POiE2E05x\n"
			+ "RgrGdkReBFPZkofgNZHF2QKBgQDFN20l+V36glYSVZhz3M5l4jxMhW7dM3FyyF24\n"
			+ "oTpfrQJyuAI0u6QgoC/uy6lT22QAWPva1DQYdfDAISAT+a140DXUldWg4r+kfZlf\n"
			+ "zetyH7u10Ihkp3pAbYq/+EtMF8hOI8IZBBRFere6jAjOxFSLZfmngWXvj5b5i6b2\n"
			+ "lSzn/wKBgBxO+8Sv6DtRmjb/q276tiNDbGPBWn5SAeuDUwdeqxnNYghpVB1OFD+D\n"
			+ "7nUwIgpHsiHXBrVM+WdbOP01oICnDmgjSnJnS2XAwjl3Oe32R2GRR6bQVnr9EuSl\n"
			+ "YrVb9f7bDDRCSxaRDreViGMrO4Er91rNGFrqbOuRlUx6kGJzFUiy\n"
			+ "-----END RSA PRIVATE KEY-----";
	
	public static final String RSA_PUBLICA = "-----BEGIN PUBLIC KEY-----\n"
			+ "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA4wPx8zXyONfJ4Zkjkz6b\n"
			+ "0GKVNt/LNTCI/SNdfRBs2ltqZW5PB8Z+j8MEsAtvKQM+sG/p+RqW7bhUpsrfMoBE\n"
			+ "pKOnWHrwt0JQIfDioPvSqqXbfEpbdphA6bHusm1+uA7WJH3S2DoHFIHIpwhSMqEe\n"
			+ "67B7fBilTqqU2mv5ya/o5tfz/hp6vlEoDu07mgIJ22zAss74LH+EGPQ0pKPWnF2F\n"
			+ "pVDpVV/3peROFIpUHn4GvM85Lqfr7hhnf8OKuuBwNcQ72z9XE1OEbZKpCE4SXWMb\n"
			+ "INhJh9OZbwEXtLNHfIUBLgjAISkfOUGF9OPoCvFLLC0/HQ00WUc3KPRUG5LM1uTl\n"
			+ "DwIDAQAB\n"
			+ "-----END PUBLIC KEY-----";

}
