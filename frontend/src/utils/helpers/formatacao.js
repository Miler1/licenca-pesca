/* eslint-disable */

String.prototype.formatarCpfCnpj = function(){
  var v = this.toString().replace(/\D/g, '')
  if (v.length <= 11) { //CPF
    v = v.replace(/(\d{3})(\d)/, '$1.$2')
    v = v.replace(/(\d{3})(\d)/, '$1.$2')
    v = v.replace(/(\d{3})(\d{1,2})$/, '$1-$2')
  } else {
    v = v.replace(/^(\d{2})(\d)/, '$1.$2')
    v = v.replace(/^(\d{2})\.(\d{3})(\d)/, '$1.$2.$3')
    v = v.replace(/\.(\d{3})(\d)/, '.$1/$2')
    v = v.replace(/(\d{4})(\d)/, '$1-$2')
  }
  return v
}
var formatarNumero = function(number, casas, fixed){

  casas = isNaN(casas) ? 2 : casas

  if(number.toString().split('.').length === 1 && !fixed) {
    return number.toString()
  }

  var numeros = number.toFixed(casas).toString().split('.')
  var esquerdo = numeros[0].replace(/\B(?=(\d{3})+(?!\d))/g, '.')
  var direito = casas === 0 ? '' : ',' + numeros[1]
  return esquerdo + direito
}

String.prototype.formatarNumero = function(casas, fixed){

  return formatarNumero(parseFloat(this.replace(',','.')), casas, fixed)

}

String.prototype.camelize = function() {

  var str = this.toString()

  return str.replace(/(?:^\w|[A-Z]|\b\w|\s+)/g, function(match, index) {

    if (+match === 0) {
      return ''
    }
    return index === 0 ? match.toLowerCase() : match.toUpperCase()

  })
}

Number.prototype.formatarNumero = function(casas, fixed){

  return formatarNumero(this, casas, fixed)

}

String.prototype.formatarTelefone = function(){
  return this.replace(/\D/g,'').replace(/^(\d{2})(\d)/g,'($1) $2')
}


String.prototype.capitalize = function() {
  return this.toLowerCase().replace(/(?:^|\s)\S/g, function(a) { return a.toUpperCase() })
}

String.prototype.formatarCEP = function(){
  return this.replace(/\D/g,'').replace(/^(\d{5})(\d)/,'$1-$2')
}


String.prototype.deixarSomenteNumeros = function(){
  return this.toString().replace(/[\D]/g,'')
}

String.prototype.isEmail = function() {
  var emailAddress = this.toString()
  var pattern = new RegExp(/^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i)
  return pattern.test(emailAddress)
}


String.prototype.isCPF = function(){
  var cpf = this.toString().replace(/[-_./]/g,'')
  var soma
  var resto
  var i
  if ((cpf === null) || (cpf === undefined) || (cpf.length !== 11) ||
    (cpf === '00000000000') || (cpf === '11111111111') ||
    (cpf === '22222222222') || (cpf === '33333333333') ||
    (cpf === '44444444444') || (cpf === '55555555555') ||
    (cpf === '66666666666') || (cpf === '77777777777') ||
    (cpf === '88888888888') || (cpf === '99999999999') ) {
    return false
  }
  soma = 0
  for (i = 1; i <= 9; i++) {
    soma += Math.floor(cpf.charAt(i-1)) * (11 - i)
  }
  resto = 11 - (soma - (Math.floor(soma / 11) * 11))
  if ((resto === 10) || (resto === 11)) {
    resto = 0
  }
  if (resto !== Math.floor(cpf.charAt(9))) {
    return false
  }
  soma = 0

  for (i = 1; i<=10; i++) {
    soma += cpf.charAt(i-1) * (12 - i)
  }
  resto = 11 - (soma - (Math.floor(soma / 11) * 11))
  if ((resto === 10) || (resto === 11)) {
    resto = 0
  }
  if (resto !== Math.floor(cpf.charAt(10))) {
    return false
  }
  return true
}

String.prototype.isCNPJ = function () {
  var cnpj = this.toString().replace(/[-_./]/g,'')
  if (cnpj === null || cnpj === undefined || cnpj.length !== 14){
    return false
  }


  var i
  var c = cnpj.substr(0,12)
  var dv = cnpj.substr(12,2)
  var d1 = 0

  for (i = 0; i < 12; i++) {
    d1 += c.charAt(11-i)*(2+(i % 8))
  }

  if (d1 === 0) {
    return false
  }

  d1 = 11 - (d1 % 11)

  if (d1 > 9) {
    d1 = 0
  }

  if (parseInt(dv.charAt(0)) !== d1) {
    return false
  }

  d1 *= 2

  for (i = 0; i < 12; i++) {
    d1 += c.charAt(11-i)*(2+((i+1) % 8))
  }

  d1 = 11 - (d1 % 11)

  if (d1 > 9){
    d1 = 0
  }

  if (parseInt(dv.charAt(1)) !== d1){
    return false
  }

  return true
}


String.prototype.convertData = function(){

  var parts =this.toString().split('/')
  return new Date(parts[2],parts[1]-1,parts[0])

}

String.prototype.exibirAreaEmHectares = function() {

  if(this && this > 0) {

    return this.formatarNumero(4) + ' ha'
  }

  return ' - '
}


String.prototype.formatarGD = function(sinal) {

  // Devemos subtrair 1 pois não consideramos o sinal na lógica de formatação
  var posicaoPonto = this.indexOf('.')-1

  var numeros = this.replace(/[^0-9]/g,'')

  var length =  numeros.length


  if(length <= 2 && posicaoPonto < 0) {

    return sinal+numeros
  }

  var re = new RegExp('^(\\d{'+posicaoPonto+'})(\\d*)','g')

  if (posicaoPonto === 1 ) {



    return sinal+numeros.substr(0,9).replace(re,'$1.$2').substr(0, posicaoPonto+8)

  }

  if (posicaoPonto === 2 ) {

    return sinal+numeros.substr(0,9).replace(re,'$1.$2').substr(0, posicaoPonto+7)

  }

  if(length > 2) {

    return sinal+numeros.substr(0,2)

  }

  return this

}

String.prototype.formatarUTM  = function() {

  var length = this.length

  var posicaoPonto = this.indexOf('.')

  if (length === 1) {

    return this.replace(/[^0-9]/g,'').replace(/^(\d{1})/g,'$1')

  }

  // Verifiando se existe um ponto

  if (posicaoPonto > 0 && posicaoPonto <= 14) {

    var re = new RegExp('^(\\d{'+posicaoPonto+'})(\\d*)','g')

    return this.replace(/[^0-9]/g,'').substr(0,16).replace(re,'$1.$2').substr(0, posicaoPonto+3)
  }

  else {

    return this.replace(/[^0-9]/g,'').substr(0,16).replace(/^(\d*)/g,'$1')
  }

  return this
}


String.prototype.formatarGMS = function(direcaoCardinal, lat, long) {

  var length = this.replace(/[^0-9]/g,'').length

  if(length === 0) {

    if (direcaoCardinal) {

      if (lat) {

        if (direcaoCardinal === 'N' || direcaoCardinal === 'S') {

          return direcaoCardinal
        }
      }

      if (long) {

        //Como estamos trabalhando com o Pará, para o sisteam GMS usando apenas o W (West)
        if (direcaoCardinal === 'W') {

          return direcaoCardinal
        }
      }

    }

    return ''
  }

  if (this.length === 1 || length === 1) {

    return this.replace(/[^0-9]/g,'').replace(/^(\d{1})/g, direcaoCardinal + ' $1')
  }

  if (length === 2) {

    return this.replace(/[^0-9]/g,'').replace(/^(\d{2})/g, direcaoCardinal + ' $1°')
  }

  if (length === 3) {

    return this.replace(/[^0-9]/g,'').replace(/^(\d{2})(\d{1})/g, direcaoCardinal + ' $1°$2')
  }

  if (length === 4) {

    return this.replace(/[^0-9]/g,'').replace(/^(\d{2})(\d{2})/g, direcaoCardinal + ' $1°$2\'')
  }

  if (length === 5) {

    return this.replace(/[^0-9]/g,'').replace(/^(\d{2})(\d{2})(\d{1})/g, direcaoCardinal + ' $1°$2\'$3')
  }

  if (length === 6) {

    return this.replace(/[^0-9]/g,'').replace(/^(\d{2})(\d{2})(\d{2})/g, direcaoCardinal + ' $1°$2\'$3.')
  }

  if (length === 7) {

    return this.replace(/[^0-9]/g,'').substr(0,10).replace(/^(\d{2})(\d{2})(\d{2})(\d{1})/g, direcaoCardinal + ' $1°$2\'$3.$4')

  }

  if (length === 8) {

    return this.replace(/[^0-9]/g,'').substr(0,10).replace(/^(\d{2})(\d{2})(\d{2})(\d{2})/g, direcaoCardinal + ' $1°$2\'$3.$4')

  }

  if (length === 9) {

    return this.replace(/[^0-9]/g,'').substr(0,10).replace(/^(\d{2})(\d{2})(\d{2})(\d{3})/g, direcaoCardinal + ' $1°$2\'$3.$4')
  }

  if (length === 10 ){

    return this.replace(/[^0-9]/g,'').substr(0,10).replace(/^(\d{2})(\d{2})(\d{2})(\d{4})/g, direcaoCardinal + ' $1°$2\'$3.$4"')
  }


  if (length > 10) {

    return this.substr(0, 15)
  }


  return this.replace(/[^0-9]/g,'')
}

String.prototype.convertGMSToDec = function () {

  var gms = this.toString()

  var sinal = (gms[0] ==='S' || gms[0] ==='W') ? '-' : '+',

    gmsDados = gms.match(/([0-9]\.?)+/g),

    graus = Number(gmsDados[0]),

    minutos = Number(gmsDados[1]),

    segundos = Number(gmsDados[2]),

    parteInteira = graus,

    parteDecimal = (minutos +segundos) > 0  ? ((minutos/60+segundos/3600) + '').split('.')[1] : '00' ,

    resultado =  Number(sinal+parteInteira+'.'+parteDecimal).toFixed(5)

  if (isNaN(resultado))  {

    throw 'Não é uma coordenada válida!'
  }

  return resultado

}

String.prototype.removeAcentos = function() {

  var comAcento = 'áàãâäéèêëíìîïóòõôöúùûüçÁÀÃÂÄÉÈÊËÍÌÎÏÓÒÕÖÔÚÙÛÜÇ'
  var semAcento = 'aaaaaeeeeiiiiooooouuuucAAAAAEEEEIIIIOOOOOUUUUC'
  var nova = ''

  for(var i = 0; i < this.length; i++) {

    if (comAcento.indexOf(this.substr(i,1)) >= 0) {

      nova += semAcento.substr(comAcento.indexOf(this.substr(i,1)),1)

    } else {

      nova += this.substr(i,1)

    }
  }

  return nova
}

String.prototype.removerPontos = function() {

  return this.toString().replace(/\./g, '')

}

Date.prototype.convertDate = function() {
  var d = this,
    month = '' + (d.getMonth() + 1),
    day = '' + d.getDate(),
    year = d.getFullYear()

  if (month.length < 2){
    month = '0' + month
  }
  if (day.length < 2) {
    day = '0' + day
  }

  return [day, month, year].join('/')
}

Date.prototype.convertDateHour = function() {
  var d = this,
    month = '' + (d.getMonth() + 1),
    day = '' + d.getDate(),
    year = d.getFullYear()

  var hours = '' + (d.getHours()),
    minutes = '' + d.getMinutes(),
    seconds = d.getSeconds()

  if (hours.length < 2){
    hours = '0' + hours
  }

  if (minutes.length < 2){
    minutes = '0' + minutes
  }

  if (seconds.length < 2){
    seconds = '0' + seconds
  }

  if (month.length < 2){
    month = '0' + month
  }
  if (day.length < 2) {
    day = '0' + day
  }

  var data = [day, month, year].join('/')
  var hora = [hours, minutes, seconds].join(':')
  return data + ' ' + hora
}

Date.prototype.subtrairDias = function(dias){
  return new Date(this.getTime() - (dias * 24 * 60 * 60 * 1000))
}


Date.prototype.diaDaSemana = function(){
  var days = ['Domingo','Segunda-feira','Terça-feira','Quarta-feira','Quinta-feira','Sexta-feira','Sábado']
  return days[this.getDay()]
}


Date.prototype.humanForm = function(){

  var monthNames = ['Janeiro','Fevereiro','Mar&ccedilo','Abril','Maio','Junho',
    'Julho','Agosto','Setembro','Outubro','Novembro','Dezembro']

  var date = this
  var day = date.getDate()
  var monthIndex = date.getMonth()
  var year = date.getFullYear()


  return day + ' de ' + monthNames[monthIndex] + ' de ' + year

}

String.prototype.minimizarTexto = function(size){

  var texto = this.toString()

  size = size || 10

  return texto.length > size ? (texto.substring(0, size).trim() + '...') : texto
}

String.prototype.minimizarCar = function(){

  var texto = this.toString()

  return texto.substring(0, 15).trim() + ' ... ' + texto.substring(39, 44).trim()

}

String.prototype.formatarCar = function(){
  var t = this.toString().replace(/[^a-z0-9]/ig, '').match(/(^\D{2})|(\d{7})|\2(.{4})/ig)
  return t.slice(0,3).join('-') + '.'+ t.slice(3).join('.')
}


Number.prototype.colocarZeroSeNecessario = function(){
  var t = '' + this.toString()
  return t.length === 1 ? '0' + t : t
}

String.prototype.pad = function(tamanho) {

  var s = this

  while (s.length < (tamanho || 5)) {
    s = '0' + s
  }

  return s.toString()

}
