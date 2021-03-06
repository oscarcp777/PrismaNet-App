package com.prismanet

import org.springframework.dao.DataIntegrityViolationException

class MailingController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
       	log.info "MailingController index params: " + params
		def nameUser=params.name==null?'':params.name
		def client=[name:nameUser]
		def data=getData()
		render(view: "email-prisma", model: [client: client, data: data])
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [mailingInstanceList: Mailing.list(params), mailingInstanceTotal: Mailing.count()]
    }

    def create() {
        [mailingInstance: new Mailing(params)]
    }

    def save() {
        def mailingInstance = new Mailing(params)
        if (!mailingInstance.save(flush: true)) {
            render(view: "create", model: [mailingInstance: mailingInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'mailing.label', default: 'Mailing'), mailingInstance.id])
        redirect(action: "show", id: mailingInstance.id)
    }

    def show(Long id) {
        def mailingInstance = Mailing.get(id)
        if (!mailingInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'mailing.label', default: 'Mailing'), id])
            redirect(action: "list")
            return
        }

        [mailingInstance: mailingInstance]
    }

    def edit(Long id) {
        def mailingInstance = Mailing.get(id)
        if (!mailingInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'mailing.label', default: 'Mailing'), id])
            redirect(action: "list")
            return
        }

        [mailingInstance: mailingInstance]
    }

    def update(Long id, Long version) {
        def mailingInstance = Mailing.get(id)
        if (!mailingInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'mailing.label', default: 'Mailing'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (mailingInstance.version > version) {
                mailingInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'mailing.label', default: 'Mailing')] as Object[],
                          "Another user has updated this Mailing while you were editing")
                render(view: "edit", model: [mailingInstance: mailingInstance])
                return
            }
        }

        mailingInstance.properties = params

        if (!mailingInstance.save(flush: true)) {
            render(view: "edit", model: [mailingInstance: mailingInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'mailing.label', default: 'Mailing'), mailingInstance.id])
        redirect(action: "show", id: mailingInstance.id)
    }

    def delete(Long id) {
        def mailingInstance = Mailing.get(id)
        if (!mailingInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'mailing.label', default: 'Mailing'), id])
            redirect(action: "list")
            return
        }

        try {
            mailingInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'mailing.label', default: 'Mailing'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'mailing.label', default: 'Mailing'), id])
            redirect(action: "show", id: id)
        }
    }
	private def getData(){
		def data =[:]
		data.type='week'
		data.fromDate='11/03'
		data.toDate='17/03'
		data.tentFrom='04/03'
		data.tentTo='10/03'
		
		data.tweets1=47472
		data.author1=15218
		data.day1='17/03'
		data.wordsDay1='argentina, presidente, sanzernesto, cambio, si, danielscioli, pro, elisacarrio, horaciorlarreta, mariuvidal, cepo, radiomitre, proargentina, sergiomassa'
		data.hour1='16/03 12hs'
		data.wordsHour1='cambio, sanzernesto, elisacarrio, mariuvidal, presidente, proargentina, mauricioyvos, futuro, pro, equipo, horaciorlarreta'
		data.tent1='55%'
		data.postLikes1=38356
		data.postComments1=1823
		data.postLink1='https://www.facebook.com/55432788477/posts/10153193620693478'
		data.postPhoto1='https://fbcdn-sphotos-a-a.akamaihd.net/hphotos-ak-xpa1/v/t1.0-9/s480x480/10420753_10153193617358478_4227045661516428873_n.jpg?oh=b1498f9bf36a5523a5c036312c0ae827&oe=55B62173&__gda__=1437524364_3fb489fcfce8bd3e37053807b5fa965d'
		
		data.tweets2=39888
		data.author2=9551
		data.day2='12/03'
		data.wordsDay2='sciolienplanm, maximmontenegro, olanaranjamza, prensaciurca, taller, canal26noticias, randazzof, jovenesxscioli, pymes, planm, presidente, social'
		data.hour2='15/03 22hs'
		data.wordsHour2='scioliconmajul, lacornisaok, majulluis, americatv, randazzof, presidente, acollia'
		data.tent2='8.23%'
		data.postLikes2=4328
		data.postComments2=288
		data.postLink2='https://www.facebook.com/39940248786/posts/10153234814818787'
		data.postPhoto2='https://fbcdn-sphotos-h-a.akamaihd.net/hphotos-ak-xfp1/v/t1.0-9/s851x315/10981537_10153234813228787_5917119666945034665_n.jpg?oh=466daec46c84aa524f8c15abac2b0f22&oe=55B46B4D&__gda__=1438065588_59fb36f9d16d24294ea89f6c30abc969'

		data.tweets3=33855
		data.author3=9559
		data.day3='12/03'
		data.wordsDay3='massaprensa, massaconfantino, fantinofantino, presidente, animalessueltos, animalesoficial, argentina, mauriciomacri'
		data.hour3='12/03 00hs'
		data.wordsHour3='massaconfantino, massaprensa, fantinofantino, presidente, animalessueltos, animalesoficial, vuelta'
		data.tent3='62.2%'
		data.postLikes3=6175
		data.postComments3=422
		data.postLink3='https://www.facebook.com/374082229927/posts/10153338282869928'
		data.postPhoto3='https://scontent-gru.xx.fbcdn.net/hphotos-xpa1/v/t1.0-9/s851x315/11073573_10153338282549928_8990146294847614603_n.jpg?oh=93ed2528d29c54406671a188a25d4686&oe=55B3BCDD'
		
		data.tweets4=26213
		data.author4=8612
		data.day4='12/03'
		data.wordsDay4='prensarandazzo, danielscioli, todoscflorencio, conferencia, medios, tren, casarosadaar, prensa, cordoba, candidato, cfkargentina, trenes'
		data.hour4='13/03 00hs'
		data.wordsHour4='prensarandazzo, c5n, apetchecopar_ok, cfkargentina, clarín, randazzoenc5n, tren, país, baby, cordoba'
		data.tent4='7.59%'
		data.postLikes4=4328
		data.postComments4=588
		data.postLink4='https://www.facebook.com/125621324134352/posts/995485350481274'
		data.postPhoto4='https://fbcdn-sphotos-b-a.akamaihd.net/hphotos-ak-xap1/v/t1.0-9/s480x480/10522559_995484643814678_5907636100978866577_n.jpg?oh=02d6bdb92719700af7dc8fc66e288053&oe=5571A530&__gda__=1434870638_da8b03953639d6e35daa97cc4f826d69'

		
		data
	}
	def send={
		log.info "MailingController send "
		def clients = [
						[mail:"santiagodonikian@gmail.com", name: "Sr. Santiago Donikian"],
						[mail:"caceres.oscar7@gmail.com", name: "Sr. Oscar Caceres"],
						[mail:"info@ibarometro.com", name: "Sr. Ignacio Ramirez"],
						[mail:"aresco@aresco.com", name: "Sr. Julio Aurelio Aresco"],
						[mail:"info@myfconsultora.com.ar", name: "Sres. MyF Consultora"],
						[mail:"alejandra.litterio@gmail.com", name: "Sra. Alejandra Litterio"],
						[mail:"info@carlosfarayasoc.com.ar", name: "Sres. Carlos Fara y Asociados"],
						[mail:"mendezgustavoe@gmail.com", name: "Sr. Gustavo Mendez"],
						[mail:"raularagon@raularagon.com.ar", name: "Sr. Raúl Aragón"]
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
