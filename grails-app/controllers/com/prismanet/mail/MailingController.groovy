package com.prismanet.mail

class MailingController {



	def index() {
		log.info "MailingController index params: " + params
		def client=[name:params.name]
		def data=getData()
		render(view: "email-prisma", model: [client: client, data: data])
	}
	private def getData(){
		def data =[:]
		data.from='12/03/2015'
		data.to='17/03/2015'
		data.tweets=11234
		data.author=5023
		
		data
	}
	def send={
		log.info "MailingController send "
		def clients = [[mail:"caceres.oscar7@gmail.com", name: "Oscar Caceres"]]
		def data=getData()
		/*[[mail:"sdonikian@prisma-net.com.ar", name: "Santiago Donikian"],
			           [mail:"santiagodonikian@gmail.com", name: "Santiago L Donikian"]]*/
		for (def client : clients)
			sendMail{
				async true
				to client.mail
				from "sdonikian@prisma-net.com.ar"
				//			cc "santiagodonikian@gmail.com"
				subject "PrismaNet - Politicos en las redes sociales"
				html view: "/mailing/email-prisma", model: [client: client, data: data]

			}
			render "mail enviado correctamente"
	}
}
