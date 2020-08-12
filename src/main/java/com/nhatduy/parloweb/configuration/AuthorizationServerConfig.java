//package com.nhatduy.parloweb.configuration;

//@Configuration
//@EnableAuthorizationServer
//public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;
//
//    @Autowired
//    @Qualifier("authenticationManager")
//    private AuthenticationManager authenticationManager;
//
//    public AuthorizationServerConfig() {
//        super();
//    }
//
//    @Override
//    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//        security.tokenKeyAccess("permitAll()")
//                .checkTokenAccess("isAuthenticated()");
//    }
//
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.inMemory().withClient("duy")
//                .secret(passwordEncoder.encode("12345"))
//                .scopes("read","write")
//                .authorizedGrantTypes("password","refresh_token")
//                .accessTokenValiditySeconds(3600)
//                .refreshTokenValiditySeconds(3600);
//    }
//
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        endpoints.authenticationManager(authenticationManager)
//                .tokenStore(tokenStore())
//                .accessTokenConverter(accessTokenConverter());
//    }
//
//    @Bean
//    public JwtTokenStore tokenStore() {
//        return new JwtTokenStore(accessTokenConverter());
//    }
//
//    @Bean
//    public JwtAccessTokenConverter accessTokenConverter() {
//        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
//        return jwtAccessTokenConverter;
//    }
//}

