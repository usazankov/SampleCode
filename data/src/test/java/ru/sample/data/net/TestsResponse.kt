package ru.sample.data.net

class ServiceDescrRespnonse{
    companion object{
        val coverUrl = "main_image.png"
        val descr = "Описание сервиса в двух-трех предложениях. Цель этого описания быстро объяснить как здорово пользоваться этим сервисом. Какие-то слова для утверждения, что сервис лучше кардридеров от банков."
        val logoUrl = "logo.png"
        val name = "SoftPos"
        val phone = "+7 (111) 222 3333"
        val JSON_SERVICE_DESCR: String = "{\"coverUrl\":\"$coverUrl\",\"description\":\"$descr\",\"logoUrl\":\"$logoUrl\",\"name\":\"$name\",\"technicalSupportPhone\":\"$phone\"}"
    }
}

class BankListResponse{
    companion object{
        val color_1 = "#FFE736"
        val coverUrl_1 = "https://10.129.106.130:8443/softpos/images/logo_tinkoff.png"
        val fullName_1 = "Публичное акционерное общество «ТИНЬКОФФ»"
        val id_1 = 0
        val shortName_1 = "Тинькофф"
        val smallIcon_1 = "https://10.129.106.130:8443/softpos/images/logo_tinkoff_small.png"

        val color_2 = "#FFE736"
        val coverUrl_2 = "https://10.129.106.130:8443/softpos/images/logo_tinkoff.png"
        val fullName_2 = "Публичное акционерное общество «ТИНЬКОФФ»"
        val id_2 = 1
        val shortName_2 = "Тинькофф"
        val smallIcon_2 = "https://10.129.106.130:8443/softpos/images/logo_tinkoff_small.png"

        val JSON_BANK_LIST: String = "[{\"color\":\"$color_1\",\"coverUrl\":\"$coverUrl_1\",\"fullName\":\"$fullName_1\",\"id\":$id_1,\"shortName\":\"$shortName_1\",\"smallIconUrl\":\"$smallIcon_1\"},{\"color\":\"$color_2\",\"coverUrl\":\"$coverUrl_2\",\"fullName\":\"$fullName_2\",\"id\":$id_2,\"shortName\":\"$shortName_2\",\"smallIconUrl\":\"$smallIcon_2\"}]"
    }
}

class BankDescrRespnonse{
    companion object{
        val offer = "Общие положения 1.1. Настоящая оферта, является официальным предложением ООО Генератор.юа, далее по тексту — «Продавец», заключить Договор купли-продажи товаров дистанционным способом, то есть через Интернет-магазин, далее по тексту — «Договор», и размещает Публичную оферту (предложение) на официальном интернет-сайте Продавца «https://prolum.com.ua (далее - «Интернет-сайт»). 1.2. Моментом полного и безусловного принятия Покупателем предложения Продавца (акцептом) заключить электронный договор купли-продажи товаров, считается факт оплаты Покупателем заказа на условиях настоящего Договора, в сроки и по ценам, указанным на Интернет-сайте Продавца. Понятия и определения 2.1. В настоящей оферте, если контекст не требует иного, нижеприведенные термины имеют следующие значения: * «товар» - модели, аксессуары, комплектующие и сопроводительные предметы; * «Интернет-магазин» - в соответствии с Законом Украины «об электронной коммерции», средство для представления или реализации товара, работы или услуги путем совершения электронной сделки. * «Продавец» - компания, реализующая товары, представленные на Интернет-сайте. * «Покупатель» - физическое лицо, заключившее с Продавцом Договор на условиях, изложенных ниже. * «Заказ» - выбор отдельных позиций из перечня товаров, указанных Покупателем при размещении заказа и проведении оплаты. Предмет Договора 3.1. Продавец обязуется передать в собственность Покупателя Товар, а Покупатель обязуется оплатить и принять Товар на условиях данного Договора. Настоящий Договор регулирует куплю-продажу товаров в Интернет-магазине, в том числе: - добровольный выбор Покупателем товаров в Интернет-магазине; - самостоятельное оформление Покупателем заказа в Интернет-магазине; - оплата Покупателем заказа, оформленного в Интернет-магазине; - обработка и доставка заказа Покупателю в собственность на условиях настоящего Договора. Порядок оформления заказа 4.1. Покупатель имеет право оформить заказ на любой товар, представленный на Сайте Интернет-магазина и имеющийся в наличии. 4.2. Каждая позиция может быть представлена в заказе в любом количестве. 4.3. При отсутствии товара на складе, Менеджер компании обязан поставить Покупателя в известность (по телефону или через электронную почту). 4.4. При отсутствии товара Покупатель вправе заменить его товаром аналогичной модели, отказаться от данного товара, аннулировать заказ. Порядок оплаты заказа Наложенным платежом 5.1. Оплата осуществляется по факту получения товара в отделении транспортных компании за наличный расчет в гривнах. 5.2. При не поступлении денежных средств Интернет-магазин оставляет за собой право аннулировать заказ. Условия доставки заказа 6.1. Доставка товаров, приобретенных в Интернет-магазине, осуществляется до складов транспортных компаний, где и производится выдача заказов. 6.2. Вместе с заказом Покупателю предоставляются документы согласно законодательства Украины. Права и обязанности сторон: 7.1. Продавец имеет право: - в одностороннем порядке приостановить оказание услуг по настоящему договору в случае нарушения Покупателем условий настоящего договора. 7.2. Покупатель обязан: - своевременно оплатить и получить заказ на условиях настоящего договора. 7.3. Покупатель имеет право: - оформить заказ в Интернет-магазине; - оформить электронный договор; - требовать от Продавца исполнения условий настоящего Договора. Ответственность сторон 8.1. Стороны несут ответственность за невыполнение или ненадлежащее выполнение условий настоящего договора в порядке, предусмотренном этим договором и действующим законодательством Украины. 8.2. Продавец не несет ответственности за: - измененный производителем внешний вид Товара; - за незначительное несоответствие цветовой гаммы товара, что может отличаться от оригинала товара исключительно из-за разной цветовой передачи мониторов персональных компьютеров отдельных моделей; - за содержание и правдивость информации, предоставляемой Покупателем при оформлении заказа; - за задержку и перебои в предоставлении Услуг (обработки заказа и доставке товара), которые происходят по причинам, находящимся вне сферы его контроля; - за противоправные незаконные действия, осуществленные Покупателем с помощью данного доступа к сети Интернет; - за передачу Покупателем своих сетевых идентификаторов - IP, MAC-адреса, логина и пароля третьим лицам; 8.3. Покупатель, используя предоставленный ему доступ к сети Интернет, самостоятельно несет ответственность за вред, причиненный его действиями (лично, даже если под его логином находилось другое лицо) лицам или их имуществу, юридическим лицам, государству или моральным принципам моральности. 8.4. В случае наступления обстоятельств непреодолимой силы, стороны освобождаются от выполнения условий этого договору. Под обстоятельствами непреодолимой силы для целей настоящего договора понимаются события, имеющие чрезвычайный, непредвиденный характер, которые исключают или объективно мешают исполнению настоящего договора, наступление которых Стороны не могли предвидеть и предотвратить разумными способами. 8.5. Стороны прикладывают максимум усилий для решения любых разногласий исключительно путем переговоров. Другие условия 9.1. Интернет-магазин оставляет за собой право в одностороннем порядке вносить изменения в настоящий договор при условии предварительной публикации его на сайте https://generator.ua 9.2. Интернет-магазин создан для организации дистанционного способа продажи товаров через Интернет. 9.3. Покупатель несет ответственность за достоверность указанной при оформлении заказа информации. При этом, при осуществлении акцепта (оформлении заказа и последующей оплаты товара) Покупатель предоставляет Продавцу свое безоговорочное согласие на сбор, обработку, хранение, использование своих персональных данных, в понимании ЗУ «О защите персональных данных». 9.4. Оплата Покупателем оформленного в Интернет-магазине заказа означает полное согласие Покупателя с условиями договора купли-продажи (публичной оферты) 9.5. Фактической датой электронного соглашения между сторонами есть дата принятия условий, в соответствии со ст. 11 Закона Украины «Об электронной коммерции» 9.6. Использование ресурса Интернет-магазина для предварительного просмотра товара, а также для оформления заказа для Покупателя есть бесплатным. 9.7. Информация, предоставляемая Покупателем является конфиденциальной. Интернет-магазин использует информацию о Покупателе исключительно в целях обработки заказа, отправления уведомлений Покупателю, доставки товара, осуществления взаиморасчетов и др. Порядок возврата товара надлежащего качества 10.1. Возврат товара в Интернет-магазин производится согласно действующего законодательства Украины. 10.2. Возврат товара в Интернет-магазин производится за счет Покупателя. 10.3. При возврате Покупателем товара надлежащего качества, Интернет-магазин возвращает ему уплаченную за товар денежную сумму по факту возврата товара за вычетом компенсации расходов Интернет-магазина связанных с доставкой товара Покупателю. Срок действия договора 11.1.Электронный договор считается заключенным с момента получения лицом направившим предложение заключить такой договор, ответа о принятии этого предложения в порядке, определенном частью шестой статьи 11 Закона Украины Об электронной коммерции. 11.2. До окончания срока действия этот Договор может быть расторгнут по взаимному согласию сторон до момента фактической доставки товара, путем возврата денежных средств 11.3. Стороны имеют право расторгнуть настоящий договор в одностороннем порядке, в случае невыполнения одной из сторон условий настоящего Договора и в случаях предусмотренных действующим законодательством Украины. Обращаем ваше внимание, что интернет-магазин «Prolum.com.ua» на официальном интернет-сайте https://generator.ua, имеет право, в соответствии с законодательством Украины, предоставлять право пользования интернет платформой ФЛП и юридическим лицам для реализации товара."
        val id = 0
        val fullName = "Публичное акционерное общество «ТИНЬКОФФ»"
        val coverUrl = "https://10.129.106.130:8443/softpos/images/logo_tinkoff.png"
        val smallIconUrl = "https://10.129.106.130:8443/softpos/images/logo_tinkoff_small.png"
        val shortName = "Тинькофф"
        val color = "#FFE736"
        val url = "https://www.tinkoff.ru"
        val textUrl = "Подробные условия тарифа"
        val name1 = "Открытие счета"
        val val1 = "1000Р"
        val name2 = "Ставка эквайринга"
        val val2 = "2%"
        val name3 = "Выпуск корпоративной карты"
        val val3 = "Бесплатно"
        val name4 = "Ведение счета"
        val val4 = "400Р/месяц"
        val name5 = "Стоимость одного платежного поручения"
        val val5 = "5Р"
        val countTariff = 5
        val JSON_BANK_DESCR = "{\"offer\":\"$offer\",\"tariffs\":[{\"name\":\"$name1\",\"value\":\"$val1\"},{\"name\":\"$name2\",\"value\":\"$val2\"},{\"name\":\"$name3\",\"value\":\"$val3\"},{\"name\":\"$name4\",\"value\":\"$val4\"},{\"name\":\"$name5\",\"value\":\"$val5\"}],\"textFullTariffs\":\"$textUrl\",\"urlFullTariffs\":\"$url\",\"color\":\"$color\",\"coverUrl\":\"$coverUrl\",\"fullName\":\"$fullName\",\"id\":$id,\"smallIconUrl\": \"$smallIconUrl\", \"shortName\": \"$shortName\"}"
    }
}
