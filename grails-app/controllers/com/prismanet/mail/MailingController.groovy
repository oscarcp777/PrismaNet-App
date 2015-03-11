package com.prismanet.mail

class MailingController {

	def mailService

	def index() {
		log.info "MailingController index params: " + params
		def nameUser=params.name==null?'':params.name
		def client=[name:nameUser]
		def data=getData()
		render(view: "email-prisma", model: [client: client, data: data])
	}
	private def getData(){
		def data =[:]
		data.from='04/03'
		data.to='10/03'
		data.tentFrom='25/02'
		data.tentTo='03/03'
		
		data.tweets1=30626
		data.author1=9766
		data.day1='08/03'
		data.wordsDay1='cambio, mariuvidal, gente, mmxv, presidente, pro, felizcumplepresidentedelcambio, mm2015'
		data.hour1='08/03 12hs'
		data.wordsHour1='mariuvidal, cambio, mmxv, felizcumplepresidentedelcambio, presidente, gente, pro'
		data.tent1='-7.53%'
		data.postLikes1=85532
		data.postComments1=3520
		data.postLink1='https://www.facebook.com/55432788477/posts/10153179562208478'
		data.postPhoto1='https://fbcdn-sphotos-a-a.akamaihd.net/hphotos-ak-xaf1/v/t1.0-9/p480x480/11050792_10153179558848478_8492985502725731055_n.png?oh=86968fe237c8cbbc3decc8aa95a0c4b3&oe=558E2802&__gda__=1438448631_724acb58b8a42db87cc7ac62432bc23f'
		
		data.tweets2=36853
		data.author2=11515
		data.day2='10/03'
		data.wordsDay2='sciolienc5n, c5n, gatosylvestre, gobernador, naranja, provincia, anibalpittelli, escuelas, acollia, stolbizer, propaganda'
		data.hour2='05/03 21hs'
		data.wordsHour2='minsaurralde, junto, 300, primeros, policíalocal, lomasdezamora, efectivos, funciones, egreso, ciurcac'
		data.tent2='3,97%'
		data.postLikes2=10327
		data.postComments2=352
		data.postLink2='https://www.facebook.com/danielsciolioficial/photos/a.104999958786.98272.39940248786/10153224035938787/?type=1&theater'
		data.postPhoto2='https://fbcdn-sphotos-h-a.akamaihd.net/hphotos-ak-xfa1/v/t1.0-9/s720x720/11043056_10153224035938787_3030651588692371339_n.jpg?oh=70cfeede7a8522413c11ab3d3fb3879b&oe=557BF657&__gda__=1438216457_978a5dd2fdcec95060aac3f1c08f5233'

		data.tweets3=20873
		data.author3=7391
		data.day3='08/03'
		data.wordsDay3='massaenlacornisa, massaprensa, lacornisaok, majulluis, americatv, mauriciomacri, presidente'
		data.hour3='08/03 22hs'
		data.wordsHour3='massaenlacornisa, massaprensa, lacornisaok, majulluis, americatv, presidente, perpetua, justicia'
		data.tent3='-15.42%'
		data.postLikes3=20157
		data.postComments3=935
		data.postLink3='https://www.facebook.com/374082229927/posts/10153310903174928'
		data.postPhoto3='https://fbcdn-sphotos-c-a.akamaihd.net/hphotos-ak-xpa1/v/t1.0-9/s480x480/10384751_10153310902989928_8883112970343545771_n.jpg?oh=4053c0e188d5bcd8de6a712010d4f8e7&oe=55897884&__gda__=1433997691_6224f1962eb207537995727fa6ea4dce'

		data.tweets4=24363
		data.author4=8466
		data.day4='05/03'
		data.wordsDay4='cfkargentina, randazzoen678, 678elprograma, nuevos, trenroca, coches, trenes, roca, sueños, viejos, fierros, trenesargentinos'
		data.hour4='05/03 21hs'
		data.wordsHour4='randazzoen678, 678elprograma, cfkargentina, todoscflorencio, presidente, tv_publica, lealtad, trenes, tren, candidato, prensainterior'
		data.tent4='2.56%'
		data.postLikes4=40282
		data.postComments4=1879
		data.postLink4='https://www.facebook.com/125621324134352/posts/990249807671495'
		data.postPhoto4='https://fbcdn-sphotos-h-a.akamaihd.net/hphotos-ak-xpa1/v/t1.0-9/10941034_990246127671863_1324178622215605428_n.jpg?oh=f207a49ac0487c688e1b233f4028e1dd&oe=55769B2B&__gda__=1433942163_2220b04599c0fc19b7dccf9e555108d7'

		
		data
	}
	def send={
		log.info "MailingController send "
		def clients = [[mail:"caceres.oscar7@gmail.com", name: "Sr. Oscar Caceres"],
						[mail:"santiagodonikian@gmail.com", name: "Sr. Santiago Donikian"]
						/*,
						[mail:"raularagon@raularagon.com.ar", name: "Sr. Raúl Aragón"]*/
						]
		def data=getData()
		/*[[mail:"sdonikian@prisma-net.com.ar", name: "Santiago Donikian"],
			           [mail:"santiagodonikian@gmail.com", name: "Santiago L Donikian"]]*/
		for (def client : clients)
			mailService.sendMail{
				async true
				to client.mail
				from "info@prisma-net.com.ar"
				//			cc "santiagodonikian@gmail.com"
				subject "Prisma-Net, Politicos en las redes sociales"
				html view: "/mailing/email-prisma", model: [client: client, data: data]

			}
			render "mail enviado correctamente"
	}
}
