get "/favicon.ico", redirect: "/img/favicon.ico"

get "/", forward: "/WEB-INF/pages/index.gtpl"

// LOGIN
get "/login", forward: "/controller/login/login.groovy"
// RANKING
get "/ranking",          forward: "/controller/motorista/rankingMotorista.groovy"
// RANKING
get "/dashboard",          forward: "/controller/dashboard/dashboard.groovy"

// TWILIO
get "/ranking/json",          forward: "/controller/motorista/rankingMotoristaJson.groovy"
get "/twilio", forward: "/twilio/twilio.groovy"
get "/twilio_callback", forward: "/twilio/twilio_callback.groovy"

// MOTORISTAS
get "/motoristas", 							forward: "/controller/motorista/listMotorista.groovy"
get "/motorista/list",          forward: "/controller/motorista/listMotorista.groovy"
get "/motorista/add",           forward: "/controller/motorista/editMotorista.groovy"
post "/motorista/insert",       forward: "/controller/motorista/insertMotorista.groovy"
get "/motorista/view/@id",      forward: "/controller/motorista/editMotorista.groovy?id=@id&view=true"
get "/motorista/edit/@id",      forward: "/controller/motorista/editMotorista.groovy?id=@id"
get "/motorista/delete/@id",    forward: "/controller/motorista/deleteMotorista.groovy?id=@id"
post"/motorista/update",        forward: "/controller/motorista/updateMotorista.groovy"
get "/motorista/search",    	  forward: "/controller/motorista/searchMotorista.groovy"
post "/motorista/sms/send",    	  forward: "/controller/motorista/smsMotorista.groovy"
post "/motorista/sms/group",    	  forward: "/controller/motorista/smsMotoristaGroup.groovy"

// GROUPS
get "/grupos", 									forward: "/controller/grupo/listGroup.groovy"
get  "/grupo/list",           	forward: "/controller/grupo/listGroup.groovy"
get  "/grupo/add",            	forward: "/controller/grupo/editGroup.groovy"
post "/grupo/insert",        		forward: "/controller/grupo/insertGroup.groovy"
get  "/grupo/delete/@id",     	forward: "/controller/grupo/deleteGroup.groovy?id=@id"
get  "/grupo/edit/@id",       	forward: "/controller/grupo/editGroup.groovy?id=@id"
post "/grupo/update",         	forward: "/controller/grupo/updateGroup.groovy"

// PONTOS
get "/pontos", 				   forward: "/controller/pontos/listPontos.groovy"
get "/pontos/add/@id/@pontos", forward: "/controller/pontos/addPontos.groovy?id=@id&pontos=@pontos"
post "/pontos/add", forward: "/controller/pontos/addPontos.groovy"

// FEEDBACK
post "/curti",           forward: "/controller/feedback/addCurti.groovy"
post "/naocurti",        forward: "/controller/feedback/addNaoCurti.groovy"
post "/sms/send",        forward: "/controller/sms/send.groovy"

// PONTOS
get "/pontos", 				   forward: "/controller/pontos/listPontos.groovy"

// LOG
get "/log", 				     forward: "/controller/log/listLog.groovy"

// SMS
get "/sms",    forward: "/controller/motorista/smsMotoristaSelect.groovy"
get "/ranking/sms", 				   forward: "/controller/sms/rankingSMS.groovy"
