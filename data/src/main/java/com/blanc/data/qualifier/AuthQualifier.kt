package com.blanc.data.qualifier

import javax.inject.Qualifier


@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class Jwt

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class Id