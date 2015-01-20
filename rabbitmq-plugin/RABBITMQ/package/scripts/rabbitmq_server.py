from resource_management import *
import sys

class RabbitMQServer(Script):

  def install(self, env):
    self.install_packages(env)
    import params
    env.set_params(params)
    daemon_cmd = format('{rabbitmq_plugins_bin} enable rabbitmq_management')
    Execute(daemon_cmd)

  def configure(self, env):
    import params
    env.set_params(params)

  def start(self, env):
    import params
    env.set_params(params)
    self.configure(env)
    daemon_cmd = format('{rabbitmq_server_bin} start')
    no_op_test = format('ls {rabbitmq_server_pid_file} >/dev/null 2>&1 && ps `cat {rabbitmq_server_pid_file}` >/dev/null 2>&1')
    Execute(daemon_cmd,
            environment={'HOME': params.rabbitmq_server_home},
            not_if=no_op_test
    )

  def stop(self, env):
    import params
    env.set_params(params)
    self.configure(env)
    daemon_cmd = format('{rabbitmq_server_bin} stop')
    Execute(daemon_cmd, 
            environment={'HOME': params.rabbitmq_server_home}
    )

  def status(self, env):
    import params
    env.set_params(params)
    check_process_status(params.rabbitmq_server_pid_file)

if __name__ == "__main__":
  RabbitMQServer().execute()
