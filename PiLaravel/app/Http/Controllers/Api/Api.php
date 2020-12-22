<?php

namespace App\Http\Controllers\Api;

use Illuminate\Http\Request;
use App\Http\Controllers\Controller;
use Illuminate\Support\Facades\Config;

class Api extends Controller{
    
    public function a($user,$pass){
        $odoo = new \Edujugon\Laradoo\Odoo();
        $odoo = $odoo
        ->username($user)
        ->password($pass)
        ->db('shopping1')
        ->host('http://localhost:8069');
        Config::write('global.user',$user);
        Config::write('global.pass',$pass);
        try {
            $odoo->connect();
        } catch (\Throwable $th) {
            return response()->json(['status' => 0]);
        }
        return response()->json(['status' => 1]);
    }

    public function login(Request $request){
        if(empty($request->username) || empty($request->password) ){
            $configUsername = Config::get('global.user');
            $configPassword = Config::get('global.pass'); 
            return $this->a($configUsername,$configPassword);
        }
        else{
          return $this->a($request->username,$request->password);  
        }
    }

    public function viewSaleOrder(){
        $configUsername = Config::get('global.user');
        $configPassword = Config::get('global.pass'); 
        $data = $this->a($configUsername,$configPassword);
        $ok = array("status" => 1);
        $notok = array("status" => 0);
        if(json_encode($data->getData()) == json_encode($ok) ){
            $odoo = new \Edujugon\Laradoo\Odoo();
            $odoo = $odoo
                ->username(Config::get('global.user'))
                ->password(Config::get('global.pass'))
                ->db('shopping1')
                ->host('http://localhost:8069');
            $odoo->connect();
            $list = $odoo->get('sale.order');
            return response()->json($list);
        }
        return response()->json('invalid credentials !');
    }
}
