yieldUnescaped '<!doctype html>'
html {
  head {
    title('My App')
    meta('http-equiv':'"Content-Type" content="text/html; charset=utf-8"')

    link(rel:'stylesheet', href:'/css/style.css')

    script(type:'text/javascript', async:'async', src:'/myapp/myapp.nocache.js') {yield ''}
  }
  body {
    noscript {
      div {
        yield 'Your web browser must have JavaScript enabled in order for this application to display correctly.'
      }
    }
    iframe(id:'__gwt_historyFrame', src:'javascript:\'\'', tabIndex:'-1') {yield ''}
  }
}
