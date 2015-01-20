#!/usr/bin/env python

from resource_management import *
import sys

class RabbitMQServiceCheck(Script):

    def service_check(self, env):
        import params
        env.set_params(params)

        print "Test status of rabbitmq server"

        daemon_cmd = format('{rabbitmq_server_bin} status')
        Execute(daemon_cmd,
          tries=3,
          try_sleep=5,
          path='/usr/sbin:/sbin:/usr/local/bin:/bin:/usr/bin',
          user=params.rabbitmq_server_user,
          environment={'HOME': params.rabbitmq_server_home},
          timeout=5,
          logoutput=True
        )

if __name__ == "__main__":
    RabbitMQServiceCheck().execute()
