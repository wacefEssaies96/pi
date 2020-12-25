<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

class Test extends Controller
{
    public function list_produit(){
        //Connect to odoo
        $odoo = new \Edujugon\Laradoo\Odoo();
        $odoo = $odoo
            ->username('wacef.stratrait@gmail.com')
            ->password('admin')
            ->db('shopping1')
            ->host('http://localhost:8069');
            $odoo->connect();

            //View all users with limit

            $users = $odoo->limit(3)->get('res.users');

            //view all sale order

            $saleOrder = $odoo->get('sale.order');

    }

    public function login(){

        $odoo = new \Edujugon\Laradoo\Odoo();
        $odoo = $odoo
            ->username('wacef.stratrait@gmail.comm')
            ->password('admin')
            ->db('shopping1')
            ->host('http://localhost:8069');
        try {
            $odoo->connect();
        } catch (\Throwable $th) {
            dump($th->getMessage());
        }

        return view('welcome');
    }
    public function test(){

        // $id = $odoo->create('res.partner',['name' => 'Jonh Odoo']);

        // $userId= $odoo->getUid();

        //$id = $odoo->where('name', 'Jonh Odoo' )->search('res.partner');

        // $models = $odoo->where('customer', true)
            // ->limit(3)
            // ->fields('name')
            // ->get('res.partner');
        // $models = $odoo
        //     ->where('is_company', '=', true)
        //     ->fields('name')
        //     ->search('res.partner');

        // $models = $odoo->call('res.partner', 'search',[
        //         [
        //             ['is_company', '=', true],
        //             ['customer', '=', true]
        //         ]
        //     ],[
        //         'offset'=>1,
        //         'limit'=>5
        //     ]);


        return view('welcome',[
            // 'userId'=>$userId,
            // 'ids'=>$ids,
            // 'models'=>$models,
        ]);
    }
}
