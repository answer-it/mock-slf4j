# mock-slf4j

This library is born for my own needs. I like to share it with the hope that it will help you too. I have been searching a while for the best approach to mock and query `static` (even `final`) loggers. The first thing one can do is to use [PowerMock](https://code.google.com/p/powermock/). That helps you to mock `static` and `final` fields. That's easy. The hard work begins after. You need to collect logged data and to query for what you want to test, most commonly for the message and the logging level. The things get more complicated when you want to take into consideration for your test the mapped diagnostic context (MDC), the markers or the message format parameters. More data has to be collected and queried. This is boring and error prone job. Here this library comes into the picture. Its goal is to help you to get rid of the hard work and let you focus on testing the logged data, which should be your main goal.


