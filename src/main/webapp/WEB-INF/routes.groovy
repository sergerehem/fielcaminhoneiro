get "/favicon.ico", redirect: "/img/favicon.ico"

get "/", forward: "/WEB-INF/pages/index.gtpl"

// LOGIN
get "/login", forward: "/controller/login/login.groovy"

// MOTORISTAS
get "/motoristas", 							forward: "/controller/motorista/listMotorista.groovy"
get "/motorista/list",          forward: "/controller/motorista/listMotorista.groovy"
get "/motorista/add",           forward: "/controller/motorista/editMotorista.groovy"
post "/motorista/insert",       forward: "/controller/motorista/insertMotorista.groovy"
get "/motorista/edit/@id",      forward: "/controller/motorista/editMotorista.groovy?id=@id"
get "/motorista/delete/@id",    forward: "/controller/motorista/deleteMotorista.groovy?id=@id"
post"/motorista/update",        forward: "/controller/motorista/updateMotorista.groovy"
get "/motorista/search",    	  forward: "/controller/motorista/searchMotorista.groovy"

// GROUPS
get "/grupos", 									forward: "/controller/grupo/listGroup.groovy"
get  "/grupo/list",           	forward: "/controller/grupo/listGroup.groovy"
get  "/grupo/add",            	forward: "/controller/grupo/editGroup.groovy"
post "/grupo/insert",        		forward: "/controller/grupo/insertGroup.groovy"
get  "/grupo/delete/@id",     	forward: "/controller/grupo/deleteGroup.groovy?id=@id"
get  "/grupo/edit/@id",       	forward: "/controller/grupo/editGroup.groovy?id=@id"
post "/grupo/update",         	forward: "/controller/grupo/updateGroup.groovy"

