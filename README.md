Este Backend trabaja con verificación de correos para lo cual se debe generar una contraseña de aplicaciones de Google
Se debe acceder al siguiente link: https://myaccount.google.com/apppasswords?rapt=AEjHL4PccetVRxeYx0mMV_Dm0MxAtWJ3h52d-FQG-z22GfvFnKdFuZQAmpNExYA07D87LSegOujBTQdPVX5cm4-eaLA_Wzp_9ab6qr_ByuLPYjhoaLcyjtY 
Se debe crear una aplicacion y copiar el codigo generado
En application properties de AuthServer se debe cambiar los siguientes parámetros
  spring.mail.username=alejopilataxi2508@gmail.com
  spring.mail.password=vsqloiaazjxccjkg
Por el correo usado para generar la aplicacion y seguido del código generado sin espacios

ESTO ES IMPORTANTE PARA QUE FUNICONE LA VERIFICACIÓN MEDIANTE CORREO.
