get "/", forward: "/WEB-INF/pages/index.gtpl"
get "/datetime", forward: "/datetime.groovy", cache: 10.seconds
get "/sqltest", forward: "/sqltest.groovy", cache: 2.minutes
get "/gpars", forward: "/gpars.groovy", cache: 30.seconds
get "/gpars2", forward: "/gpars2.groovy", cache: 30.seconds
get "/env", forward: "/env.groovy", cache: 2.minutes
get "/services", forward: "/services.groovy", cache: 2.minutes
get "/serverinfo", forward: "/serverinfo.groovy", cache: 2.minutes