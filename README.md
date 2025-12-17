# Tienda de Libros

Sistema diseñado para gestionar y automatizar la venta de libros en plataformas electrónicas, centralizando operaciones, stock, precios y publicaciones en una única plataforma robusta y escalable.

## Plataformas
Las siguientes plataformas son entidades ficticias utilizadas exclusivamente con fines de prueba y simulación dentro del proyecto.
No representan integraciones reales ni utilizan APIs oficiales de dichas plataformas.
* Amazon (simulación)
* eBay (simulación)
* Mercado Libre (simulación)

Estas plataformas se modelan únicamente para validar la arquitectura, el flujo de ventas, la comunicación entre microservicios, el uso de eventos (Kafka) y los procesos automáticos (Cron Jobs).

El objetivo es probar escenarios reales de negocio sin depender de servicios externos ni credenciales oficiales.

## Arquitectura
* La gestión de ventas está implementada mediante una arquitectura de microservicios, lo que permite:
* Escalabilidad independiente por dominio
* Menor acoplamiento entre componentes

## Comunicación entre servicios
* Apache Kafka para comunicación asíncrona basada en eventos
* Procesamiento desacoplado de ventas, stock y estados
* Manejo de eventos como:
  * Venta creada
  * Stock reservado
  * Venta completada
  * Error de procesamiento

## Procesos automáticos
* Schedulers (Cron Jobs)
  * Procesar ventas pendientes
  * Sincronizar estados y stock
